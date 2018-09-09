/*编写函数slnklist delx(linklist head, datatype x)，删除不带头结点单链表head中第一个值为x 的结点。
并构造测试用例进行测试。
*/
/**********************************/
/*文件名称：lab2_01.c             */
/**********************************/

#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist delx(linklist head,datatype x)
{

}

int main()
{   datatype x;
    linklist head;
    head=creatbyqueue();		/*尾插入法建立单链表*/
    print(head);
    printf("请输入要删除的值：");
    scanf("%d",&x);
    head=delx(head,x);			/*删除单链表的第一个值为x的结点*/
    print(head);
    delList(head);				/*释放单链表空间*/
    return 0;
}
