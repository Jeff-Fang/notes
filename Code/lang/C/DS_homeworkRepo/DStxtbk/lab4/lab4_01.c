/*
利用顺序栈结构，编写算法函数void Dto16(unsigned int m)实现十进制无符号整数m到十六进制数的转换功能。
*/
/**********************************/
/*文件名称：lab4_01.c                 */
/**********************************/
#include "seqstack.h"
/*请将本函数补充完整，并进行测试*/
void Dto16(int m)
{   seqstack s;			/*定义顺序栈*/
    init(&s);
    printf("十进制数%u对应的十六进制数是：",m);
    while (m)
    {

    }
    while (!empty(&s))
                putchar(     );
    printf("\n");
}
int main()
{    int m;
     printf("请输入待转换的十进制数：\n");
     scanf("%u",&m);
     Dto16(m);
     return 0;
}
