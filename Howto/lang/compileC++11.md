```
clang++ -std=c++11 -stdlib=libc++ -Weverything main.cpp
```

If you haven't installed the command line tools for Xcode you can run the compiler and other tools without doing that by using the `xcrun` tool.

```
xcrun clang++ -std=c++11 -stdlib=libc++ -Weverything main.cpp
```

Also if there's a particular warning you want to disable you can pass additional flags to the compiler to do so. At the end of the warning messages it shows you the most specific flag that would enable the warning. To disable that warning you prepend `no-` to the warning name.

For example you probably don't want the c++98 compatibility warnings. At the end of those warnings it shows the flag `-Wc++98-compat` and to disable them you pass `-Wno-c++98-compat`.

If you want to use some GNU extensions (and also use C++11), you can use -std=gnu++11 instead of -std=c++11, which will turn on C++11 mode and also keep GNU extensions enabled.
