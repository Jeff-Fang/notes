mov	dx, 0123h       ;8253的输入端口
mov al, 00110100B   ;8253控制字，表示0号计数器，16位，2号方式，二进制
out dx, al          ;输出控制字

mov dx, 0120h       ;8253的0号计数器端口
mov ax, 20000       ;20000个脉冲触发
out dx, al
mov al, ah
out dx, al
