cmake_minimum_required(VERSION 3.10)

project(save2disk)


add_executable(save2disk rs-save-to-disk.cpp stb_image_write.h)
target_link_libraries(save2disk PUBLIC realsense2)
add_executable(rs_save rs-save-ir.cpp stb_image_write.h)
target_link_libraries(rs_save PUBLIC realsense2)

add_executable(adv_opt_test advanced_mode.cpp)
target_link_libraries(adv_opt_test PUBLIC realsense2)
