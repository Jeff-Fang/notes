/*编写函数void delx(linklist head, datatype x)，删除带头结点单链表head中第一个值为x 的结点。
并构造测试用例进行测试。
*/
/**********************************/
/*文件名称：lab3_01.c                 */
/**********************************/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist delx(linklist head,datatype x)
{   linklist pre = head;
    linklist point = head->next;
    while (point->info != x){
        pre = point;
        point = point->next;
    }
    pre->next = point->next;
    return head;
}

int main()
{   datatype x;
    linklist head;
    head=creatbyqueue();		/*尾插入法建立带头结点的单链表*/
    print(head);
    printf("please insert the number to be deleted:");
    scanf("%d",&x);
    head=delx(head,x);			/*删除单链表的第一个值为x的结点*/
    print(head);
    delList(head);				/*释放单链表空间*/
    return 0;
}
