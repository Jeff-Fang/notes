/*
利用链式栈结构，编写算法函数void Dto16(unsigned int m)实现十进制无符号整数m到十六进制数的转换功能。
*/
/**********************************/
/*文件名称：lab4_02.c                 */
/**********************************/
#include "linkstack.h"
/*请将本函数补充完整，并进行测试*/
void Dto16(unsigned int m)
{
    linkstack s;
    s=init();
    printf("十进制数%u对应的十六进制数是：",m);
    while (m)
    {
    }
    while (!empty(s))
               {

               }
    printf("\n");
}


int main()
{
        unsigned int m;
        printf("请输入待转换的十进制数：\n");
        scanf("%u",&m);
        Dto16(m);
        return 0;
}

