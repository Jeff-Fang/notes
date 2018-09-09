/*

编写算法函数void preorder1(bintree t)实现二叉树t的非递归前序遍历。

*/

#include "bintree.h"
char *a="ABC##D#E##F##";  /*扩充二叉树序树t的前序序列*/

/*函数preorder1()的功能是非递归前序遍历二叉树t，请将函数补充完整并调试运行*/
void preorder1(bintree t)
{

}
int main()
{   bintree t;
    t=creatbintree();   /*建立二叉树t的存储结构*/
    printf("二叉树的前序序列为：\n");
    preorder1(t);       /*前序非递归遍历二叉树*/
    return 0;
}
