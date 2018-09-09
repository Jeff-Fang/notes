/*
假设树采用指针方式的孩子表示法表示，试编写一个函数int equal(tree t1, tree t2)，
判断两棵给定的树是否等价（两棵树等价当且仅当其根结点的值相等且其对应的子树均相互等价）。
*/
#include "tree.h"
#define TRUE  1
#define FALSE 0

int equal(tree t1,tree t2)
{

}

int main ()
{
	tree t1,t2;
    printf("please input the preorder sequence of the tree:\n");
	t1=createtree();
	getchar();
	printf("please input the preorder sequence of the tree:\n");
	t2=createtree();
    if ( equal(t1,t2) == TRUE)
	{
		printf ("两树相等\n");
	}
	else
	{
		printf ("两树不相等\n");
	}

	return 0;
}
