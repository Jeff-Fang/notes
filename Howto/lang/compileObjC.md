` clang on OS X `

$ clang -fobjc-arc files -o program

$ clang -fobjc-arc -framwork Foundation file1.m file2.m -o program



` gcc on Linux `

$ gcc -o program file.m -lobjc -lgnustep-base (-fgnu-runtime) -fconstant-string-class=NSConstantString

