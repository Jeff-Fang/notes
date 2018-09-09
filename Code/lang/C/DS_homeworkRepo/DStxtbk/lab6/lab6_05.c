/*
假设树采用指针方式的孩子表示法存储结构，试编写一个函数tree Ct(char s[])，
根据输入的树的括号表示字符串s，生成树的存储结构。例如，若要建立教材图6.4所示的树，
应输入A（B（E，F）,C,D（G（I，J，K）,H））。（说明，tree.h中定义的常量m表示树的最
大度，请根据建树的需要自行修改m的值）

*/

#include "tree.h"
/*请将本函数补充完整，并进行测试*/
tree Ct(char s[MAXLEN])
{

}

int main ()
{
    char s[MAXLEN];
	tree root = NULL;
	printf ("请用树的括号表示法输入一棵树:\n");
	scanf ("%s",s);
	root = Ct(s);
	preorder(root);  /*前序遍历树*/
	return 0;
}
