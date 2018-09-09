/*
编写算法函数void levelorder(tree t)实现树的层次遍历。
*/

#include "tree.h"

void levelorder(tree t)    /* t为指向树根结点的指针*/
{

}

 int main()
 {
   tree t;
   printf("please input the preorder sequence of the tree:\n");
   t=createtree();
   printf("\nthe levelorder is:");
   levelorder(t);
   return 0;
 }
