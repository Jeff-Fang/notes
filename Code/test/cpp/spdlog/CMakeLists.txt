project(SPDLOGTEST)

file ( GLOB SRCS *.cpp *.cc *.hpp *.c *.h )
add_executable( ${PROJECT_NAME} ${SRCS} )
target_link_libraries( ${PROJECT_NAME} spdlog )
