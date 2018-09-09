/**************************************/
/*         二叉排序树用的头文件       */
/*          文件名：bstree.h          */
/**************************************/
#include<stdio.h>
#include<stdlib.h>
typedef struct node1            /*二叉排序树结点定义*/
 {
  int key;                      /*结点值*/
  struct node1 *lchild,*rchild; /*左、右孩子指针*/
  }bsnode;
typedef bsnode *bstree;

/*---中序遍历二叉排序树----*/
void inorder(bstree t)
  { if (t) {    inorder(t->lchild);
                printf("%8d",t->key);
                inorder(t->rchild);
             }

   }
