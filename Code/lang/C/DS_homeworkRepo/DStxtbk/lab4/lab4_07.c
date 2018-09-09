/*
利用朴素模式匹配算法，将模式t在主串s中所有出现的位置存储在带头结点的单链表中。
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
typedef struct node
{		int data;
		struct node *next;
}linknode;
typedef linknode *linklist;
/*朴素模式匹配算法,返回t中s中第一次出现的位置，没找到则返回-1，请将程序补充完整*/
int index(char *s,char *t)
{

}
/*利用朴素模式匹配算法，将模式t在s中所有出现的位置存储在带头结点的单链表中,请将函数补充完整*/
linklist indexall(char *s,char *t)
{

}
/*输出带头结点的单链表*/
void print(linklist head)
{	linklist p;
	p=head->next;
	while(p)
	{	printf("%5d",p->data);
		p=p->next;
	}
	printf("\n");
}
int main()
{	char s[80],t[80];
	linklist head;
	printf("请输入主串:\n");
	gets(s);
	printf("请输入模式串:\n");
	gets(t);
	int k=index(s,t);
	printf("k=%d",k);
	head=indexall(s,t);
	printf("\n[ %s ]在[ %s ]中的位置有：\n",t,s);
	print(head);
    return 0;
}
