# CMake drives four ABIs and drops each .so into jniLibs so AGP packages it.
cmake_minimum_required(VERSION 3.22.1)
project(earlyinit LANGUAGES CXX)

add_library(earlyinit SHARED
        early_init.cpp
        earlyinit_jni.cpp
)

set_target_properties(earlyinit PROPERTIES
        LIBRARY_OUTPUT_DIRECTORY ${CMAKE_CURRENT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}
        CXX_STANDARD 17
        CXX_STANDARD_REQUIRED YES
)

find_library(log-lib log)
target_link_libraries(earlyinit ${log-lib})