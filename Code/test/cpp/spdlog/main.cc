//Create rotating file multi-threaded logger
#include <iostream>
#include "spdlog/spdlog.h"
#include "spdlog/sinks/rotating_file_sink.h"
#include "spdlog/sinks/daily_file_sink.h"
#include "spdlog/sinks/stdout_color_sinks.h" // or "../stdout_sinks.h" if no colors needed
#include "spdlog/sinks/basic_file_sink.h"

int main() {
    try {
        // auto logger = spdlog::rotating_logger_mt("file_logger", "logs/mylogfile", 1048576 * 5, 3);
        // logger->set_level(spdlog::level::debug);
        // logger->warn("this should appear in both console and file");
        // logger->info("this message should not appear in the console, only in the file");
        //
        std::string pattern="%D %T [thread %t %s:%#:%!] %^%8l%$  %v";

        auto console_sink = std::make_shared<spdlog::sinks::stdout_color_sink_mt>();
        console_sink->set_level(spdlog::level::warn);
        console_sink->set_pattern(pattern);

        auto file_sink = std::make_shared<spdlog::sinks::basic_file_sink_mt>("logs/multisink.txt", true);
        file_sink->set_level(spdlog::level::trace);
        file_sink->set_pattern(pattern);

        spdlog::sinks_init_list sink_list = { file_sink, console_sink };

        spdlog::logger logger("multi_sink", sink_list.begin(), sink_list.end());
        logger.set_level(spdlog::level::debug);

        // or you can even set multi_sink logger as default logger
        spdlog::set_default_logger(std::make_shared<spdlog::logger>("multi_sink", spdlog::sinks_init_list({console_sink, file_sink})));

        logger.warn("this should appear in both console and file");
        logger.info("this message should not appear in the console, only in the file");

        SPDLOG_WARN("22222 this should appear in both console and file");
        SPDLOG_INFO("22222 this message should not appear in the console, only in the file");

    } catch (const spdlog::spdlog_ex& ex) {
        std::cout << "Log initialization failed: " << ex.what() << std::endl;
    }
}

