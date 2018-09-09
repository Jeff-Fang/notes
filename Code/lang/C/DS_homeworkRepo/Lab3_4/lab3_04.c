/*
编写算法函数linklist delallx(linklist head, int x)，删除带头结点单链表head中所有值为x的结点。
*/
/**********************************/
/*文件名称：lab3_04.c                 */
/**********************************/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist delallx(linklist head,int x)
{   linklist pre = head;
    linklist compare = head->next;
    while(compare->info != x){
        pre = compare;
        compare = compare->next;
    }
    pre->next = compare->next;
    free (compare);
    return head;
}
int main()
{   datatype x;
    linklist head;
    head=creatbyqueue();				/*尾插入法建立带头结点的单链表*/
    print(head);
    printf("请输入要删除的值：");
    scanf("%d",&x);
    head=delallx(head,x);
    print(head);
    delList(head);
    return 0;
}
