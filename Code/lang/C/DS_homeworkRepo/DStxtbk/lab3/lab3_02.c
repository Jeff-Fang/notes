/**********************************/
/*文件名称：lab3_02.c                 */
/**********************************/
/*
假设线性表（a1,a2,a3,…an）采用带头结点的单链表存储，请设计算法函数linklist reverse(linklist  head)，
将带头结点的单链表head就地倒置，使表变成（an,an-1,…a3.a2,a1）。并构造测试用例进行测试。
*/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist reverse(linklist head)
{

}
int main()
{   datatype x;
    linklist head;
    head=creatbystack();			/*头插入法建立带头结点的单链表*/
    print(head);					/*输出原链表*/
    head= reverse(head);			/*倒置单链表*/
    print(head);					/*输出倒置后的链表*/
    delList(head);
    return 0;
}
