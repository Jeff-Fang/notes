// License: Apache 2.0. See LICENSE file in root directory.
// Copyright(c) 2015-2017 Intel Corporation. All Rights Reserved.

#include <librealsense2/rs.hpp> // Include RealSense Cross Platform API
#include <librealsense2/rs_advanced_mode.hpp>

#include <fstream>              // File IO
#include <iostream>             // Terminal IO
#include <sstream>              // Stringstreams
#include <ctime>

// 3rd party header for writing png files
#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "stb_image_write.h"

// Helper function for writing metadata to disk as a csv file
void metadata_to_csv(const rs2::frame& frm, const std::string& filename);
int get_index();

static const int DEFAULT_CAM_INDEX = 0;
static const double DEFAULT_LOOP_RATE_HZ = 7.5;
static const double HOOK_CAM_FRAME_WIDTH = 1280;
static const double HOOK_CAM_FRAME_HEIGHT = 720;
static const int MAX_CONNECTION_TRIES = 20;

static const int COLOR_CAMERA = -1;
static const int IR_CAMERA_LEFT = 1;
static const int IR_CAMERA_RIGHT = 2;
static const int D435_FRAMERATE = 6;

void printIntrinsics(const rs2_intrinsics i) {
    // std::cout << i << std::endl;
    std::cout << "fx: " << i.fx << std::endl
              << "fy: " << i.fy << std::endl
              << "ppx: " << i.ppx << std::endl
              << "ppy: " << i.ppy << std::endl << std::endl;

}

// This sample captures 30 frames and writes the last frame to disk.
// It can be useful for debugging an embedded system with no display.
int main(int argc, char * argv[]) try
{
    // Declare depth colorizer for pretty visualization of depth data
    rs2::colorizer color_map;

    // int width = 480;
    // int height = 270;
    // int fps = 6;
    rs2::config config;


    config.enable_stream(RS2_STREAM_INFRARED, IR_CAMERA_LEFT, HOOK_CAM_FRAME_WIDTH, HOOK_CAM_FRAME_HEIGHT, RS2_FORMAT_Y8, D435_FRAMERATE);
    // config.enable_stream(RS2_STREAM_INFRARED, IR_CAMERA_RIGHT, HOOK_CAM_FRAME_WIDTH, HOOK_CAM_FRAME_HEIGHT, RS2_FORMAT_Y8, D435_FRAMERATE);

    // config.enable_stream(RS2_STREAM_INFRARED, 1);
    // config.enable_stream(RS2_STREAM_INFRARED, 2);
    config.enable_stream(RS2_STREAM_COLOR, COLOR_CAMERA, HOOK_CAM_FRAME_WIDTH, HOOK_CAM_FRAME_HEIGHT, RS2_FORMAT_BGR8, D435_FRAMERATE);

    // start pipeline
    rs2::pipeline pipe;
    rs2::pipeline_profile selected_profile = pipe.start(config);

    // Configure camera options
    rs2::device selected_device = selected_profile.get_device();

    if (selected_device.is<rs400::advanced_mode>()) {
        auto advanced_mode_dev = selected_device.as<rs400::advanced_mode>();
        if (!advanced_mode_dev.is_enabled()) {
            advanced_mode_dev.toggle_advanced_mode(true);
        }
    } else {
        std::cout << "Current device doesn't support advanced mode!" << std::endl;
    }

    for (auto &profile : selected_profile.get_streams()) {
        if (profile.stream_type() == rs2_stream::RS2_STREAM_COLOR) {
            rs2::video_stream_profile stream_profile = rs2::video_stream_profile(profile);
            std::cout << "RS2_STREAM_COLOR" << std::endl;
            printIntrinsics(stream_profile.get_intrinsics());
        } else if (profile.stream_type() == rs2_stream::RS2_STREAM_INFRARED) {
            rs2::video_stream_profile stream_profile = rs2::video_stream_profile(profile);
            std::cout << "RS2_STREAM_INFRARED" << std::endl;
            printIntrinsics(stream_profile.get_intrinsics());
        }
    }


    auto depth_sensor = selected_device.first<rs2::depth_sensor>();
    if (depth_sensor.supports(RS2_OPTION_EMITTER_ENABLED)) {
        depth_sensor.set_option(RS2_OPTION_EMITTER_ENABLED, 0.f); // Disable emitter
    }
    if (depth_sensor.supports(RS2_OPTION_LASER_POWER))
    {
        depth_sensor.set_option(RS2_OPTION_LASER_POWER, 0.f); // Disable laser
    }


    // Capture 30 frames to give autoexposure, etc. a chance to settle
    for (auto i = 0; i < 30; ++i) pipe.wait_for_frames();

    int idx = get_index();
    // Wait for the next set of frames from the camera. Now that autoexposure, etc.
    // has settled, we will write these to disk
    for (auto&& frame : pipe.wait_for_frames())
    {
        // We can only save video frames as pngs, so we skip the rest
        if (auto vf = frame.as<rs2::video_frame>())
        {
            auto stream = frame.get_profile().stream_type();
            // Use the colorizer to get an rgb image for the depth stream
            if (vf.is<rs2::depth_frame>()) vf = color_map.process(frame);

            // Write images to disk
            std::stringstream png_file;
            // time_t curr_time;
            // tm * curr_tm;
            // char time_string[100];
            // time(&curr_time);
            // curr_tm = localtime(&curr_time);

            // strftime(time_string, 10, "%T", curr_tm);
            png_file << idx << "_" << vf.get_profile().stream_name() << ".png";
            stbi_write_png(png_file.str().c_str(), vf.get_width(), vf.get_height(),
                           vf.get_bytes_per_pixel(), vf.get_data(), vf.get_stride_in_bytes());
            std::cout << "Saved " << png_file.str() << std::endl;

            // // Record per-frame metadata for UVC streams
            // std::stringstream csv_file;
            // csv_file << "rs-save-to-disk-output-" << vf.get_profile().stream_name()
            //          << "-metadata.csv";
            // metadata_to_csv(vf, csv_file.str());
        }
    }

    return EXIT_SUCCESS;
}
catch(const rs2::error & e)
{
    std::cerr << "RealSense error calling " << e.get_failed_function() << "(" << e.get_failed_args() << "):\n    " << e.what() << std::endl;
    return EXIT_FAILURE;
}
catch(const std::exception & e)
{
    std::cerr << e.what() << std::endl;
    return EXIT_FAILURE;
}

void metadata_to_csv(const rs2::frame& frm, const std::string& filename)
{
    std::ofstream csv;

    csv.open(filename);

    //    std::cout << "Writing metadata to " << filename << endl;
    csv << "Stream," << rs2_stream_to_string(frm.get_profile().stream_type()) << "\nMetadata Attribute,Value\n";

    // Record all the available metadata attributes
    for (size_t i = 0; i < RS2_FRAME_METADATA_COUNT; i++)
    {
        if (frm.supports_frame_metadata((rs2_frame_metadata_value)i))
        {
            csv << rs2_frame_metadata_to_string((rs2_frame_metadata_value)i) << ","
                << frm.get_frame_metadata((rs2_frame_metadata_value)i) << "\n";
        }
    }

    csv.close();
}

int get_index()
{
    std::string index_string;
    int index = 0;

    std::ifstream index_file("index.txt");
    if (index_file.is_open()) {
        getline(index_file, index_string);
        index = std::stoi(index_string);
        std::cout << "Current index is: " << index << std::endl;
        index_file.close();
    }

    std::ofstream ofile("index.txt");
    if (ofile.is_open()) {
        index_string = std::to_string(index+1);
        ofile << index_string;
        ofile.close();
    }
    return index;

}
