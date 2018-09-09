#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
#define m 3
#define MAXLEN 100

typedef char datatype;
typedef struct node {
     datatype data;
     struct node *child[m];
} node;
typedef  node *tree;
/********************************************************/
/*  函数功能:根据树的前序遍历结果建立一棵3度树	  	   */
/*  函数返回值:树根地址								   */
/*  文件名:tree.h,函数名:createtree ()		            */
/********************************************************/

tree  createtree()
 {/*按前序遍历顺序建立一棵3度树的递归算法*/

   int i; char ch;
   tree t;
   if ((ch=getchar())=='#')  t=NULL;
   else
      {
            t=(tree) malloc (sizeof(node));
            t->data=ch;
            for (i=0;i<m;++i)
                        t->child[i]= createtree();
            }
      return t;
}

void preorder(tree t)
{
    int i;
    if (t)
        {
            printf("%c",t->data);
            for (i=0;i<m;i++)
                preorder(t->child[i]);
        }
}

void postorder(tree t)
{
    int i;
    if (t)
        {
            for (i=0;i<m;i++)
                postorder(t->child[i]);
            printf("%c",t->data);
        }
}
