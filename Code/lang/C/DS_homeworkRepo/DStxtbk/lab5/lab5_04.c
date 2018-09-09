/*
已知带头结点的单链表结构定义同实验3，假设链表中所有结点值均不相同，
请编写一个递归函数linklist max(linklist head)，返回表中最大数所在的结点地址，若链表为空，返回NULL。
*/


#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist max(linklist head)
{

}
int main()
{   linklist head,p;
    head=creatbyqueue();
    print(head);
    p=max(head);
    if (p)
        printf("max=%d\n",p->info);
    else
        printf("链表为空\n");
    return 0;
}
