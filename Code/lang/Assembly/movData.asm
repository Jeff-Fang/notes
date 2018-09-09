  mov di, 1000h   ;寄存器间接寻址的操作数只能用BX，BP，SI，DI 不能用AX!
  mov si, 2000h   ;把DI，SI指向两个内存地址，DI，SI，BX的默认段为DS，BP的默认段为SS
  mov cx, 56h     ;cx存入计数，循环100次，即56h次
start:            ;开始循环
  mov al  , [di]  ;通过AL做中介，在两个内存单元中传输数据
  mov [si], al    ;存入目标内存地址
  inc di          ;源内存地址加一
  inc si          ;目标内存地址加一
  loop start      ;循环体结束
