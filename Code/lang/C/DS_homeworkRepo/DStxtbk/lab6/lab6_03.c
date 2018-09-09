/*
   假设树采用指针方式的孩子表示法表示，试编写一个非递归函数void PostOrder1(tree t)，实现树的后序遍历算法。
*/

#include "tree.h"
int PostOrder1(tree root)
{

}
int main ()
{
    tree root;
    printf("please input the preorder sequence of the tree:\n");
	root =createtree();
	printf("后序序列是：\n");
	PostOrder1(root);
	return 0;
}

