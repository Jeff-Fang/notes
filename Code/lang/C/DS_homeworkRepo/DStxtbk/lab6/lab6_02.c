/*
假设树采用指针方式的孩子表示法表示，试编写一个非递归函数void PreOrder1(tree root)，实现树的前序遍历算法。
*/
#include "tree.h"
void  PreOrder1(tree root)
{

}
int main ()
{
        tree root;
        printf("please input the preorder sequence of the tree:\n");
		root =createtree();
		printf("前序序列是：\n");
		PreOrder1(root);
		return 0;
}
