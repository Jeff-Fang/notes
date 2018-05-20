Compile:
```bash
clang -g test01.c -o test01
```

Run lldb:
```bash
lldb test01
```

Add breakpoints:
```llvm
b 10
b main
b test01.c:10
b test01.c:main
```

Debugging Command
```llvm
run
n or next
s or step 
c or continue
finish
```

List and delete breakpoints
```llvm
br list
br del 1
br del          // deleting all breakpoints
```

Showing variables
```llvm
p x             // showing particular variable
frame variable  // showing all variables
```

Modifying variables
```llvm
expr x = 5
```

Display current line
```llvm
f
```

[Referance Website](http://www.developerfiles.com/debugging-c-with-clang-compiler-and-lldb/)
