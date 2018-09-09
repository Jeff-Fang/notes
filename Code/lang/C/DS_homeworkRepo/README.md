# dataStructure
College data structure report source code ( C language )

#### 使用
* 可以直接在网页中点开文件，直接将代码复制进 IDE ；
* 也可以使用 Github 直接下载，如果不会弄就直接用上一条。

#### 关于文件名
* Lab5_1 指的是实验五的第1小题；
* completed 文件为标记文件，可以忽略；
* 后缀为 .c 的文件为题目源文件，答案已经填进原题目；
* 后缀为 .h 的文件为该题目需要用到的头文件，编译时需要和源文件放在一起编译。

#### 关于 Windows 系统的IDE问题
学校使用的IDE是 VC++6.0 , 然而这个IDE早已过时，普遍在 win8 以上的微软系统中安装不成功。如果不成功的话可以使用以下方法：

1. [Visual Studio Community](https://www.visualstudio.com): 微软官方的IDE，体积较大，但在 windows 下操作较简单。如果出现编译不成功，可以尝试把所有的 **scanf() 函数**替换成 **scanf_s() 函数**。其他基本操作问题请自行百度。

2. [Codepad在线编译器](http://codepad.org): 一款在线编译器。可以无需安装直接在浏览器里进行编译。缺点是不支持多文件，需要手动把相关联的头文件代码复制到源文件里，并自行修改头文件调用情况。

3. 使用其他的IDE，如[Geany](http://www.geany.org)、[NetBeans IDE](https://netbeans.org/features/cpp/index.html)、[Eclipse c/c++](http://www.eclipse.org/downloads/packages/eclipse-ide-cc-developers/lunasr2) 等等。但是由于 windows 自身并没有通用的C语言编译器**GCC**，所以还需要手动下载GCC并将其链接进系统中。操作虽不是很复杂，但理解比较困难，不想太折腾的同学就不要尝试了，有兴趣折腾的非常推荐自己科学上网手动查资料搞定。

#### 关于 Linux 系统和苹果 OS X 系统
本代码是在 OS X 下使用 Vim 编辑、GCC 编译测试、运行没有问题的。所以比较推荐使用 OS X 或 Linux 系统。关于IDE，
* OS X 中可以直接使用 Xcode ；
* Linux 中直接下载诸如 [Geany](http://www.geany.org) 之类的小型 IDE 就可以完美使用。
