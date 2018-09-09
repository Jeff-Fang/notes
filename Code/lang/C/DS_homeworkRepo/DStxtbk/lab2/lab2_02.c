/**********************************/
/*文件名称：lab2_02.c                 */
/**********************************/
/*
假设线性表（a1,a2,a3,…an）采用不带头结点的单链表存储，
请设计算法函数linklist reverse1(linklist  head)和
void reverse2(linklist *head)将不带头结点的单链表head就地倒置，
使表变成（an,an-1,…a3.a2,a1）。并构造测试用例进行测试。
*/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist reverse1(linklist head)
{

}
void reverse2(linklist *head)
{

}

int main()
{   datatype x;
    linklist head;
    head=creatbystack();		/*头插入法建立单链表*/
    print(head);				/*输出原链表*/
    head= reverse1(head);		/*倒置单链表*/
    print(head);				/*输出倒置后的链表*/
    reverse2(&head);			/*倒置单链表*/
    print(head);
    delList(head);
    return 0;
}
