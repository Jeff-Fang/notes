cmake_minimum_required(VERSION 3.10)

project(hello VERSION 0.1
    DESCRIPTION "Hello Library"
    LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)


include_directories(
    src
    include
)

add_library(hello SHARED
    src/hello.cpp
)


