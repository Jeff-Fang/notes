/*
已知，二叉树存储结构定义见bstree.h，请编写一个算法函数bstree creatBstree(int a[],int n)，
以数组a中的数据作为输入建立一棵二叉排序树，并将建立的二叉排序树进行中序遍历。
（提示，a中的原始数据可从data1.txt中读入，实验代码详见lab9_05.c）

*/

#include "Arrayio.h"
#include "bstree.h"
#define N 100
bstree  creatBstree(int a[],int n)
  { /*根据输入的结点序列，建立一棵二叉排序树，并返回根结点的地址*/



  }
int  main()
  {
    int n,a[N];
    bstree p,t;
    n=readData(a,N,"data1.txt");
    output(a,n);
    t=creatBstree(a,n);         /*创建二叉排序树*/
    printf("中序遍历：\n");
    inorder(t);                 /*中序遍历二叉排序树*/

    return 0;
 }


