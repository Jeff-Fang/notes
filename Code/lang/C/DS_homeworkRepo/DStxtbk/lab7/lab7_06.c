/*
试编写一个递归函数bintree buildBintree(char *pre, char *mid, int length)，
根据二叉树的前序序列pre、中序序列mid和前序序列长度length，构造二叉树的二叉链表存储结构，
函数返回二叉树的树根地址。
*/

#include "bintree.h"
#include <string.h>
char *a="";
/*请将本函数补充完整，并进行测试*/
bintree buildBintree(char *pre, char *mid,int length)
{

}
int main()
{   bintree root;
    char pre[100],mid[100];
    puts("请输入前序序列：");
    gets(pre);
    puts("请输入中序序列：");
    gets(mid);
    root=buildBintree(pre,mid,strlen(pre));
    puts("后序序列是：");
    postorder(root);
}

