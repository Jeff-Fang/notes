/*
已知线性表存储在带头结点的单链表head中，请设计算法函数void sort(linklist head)，将head中的结点按结点值升序排列。
*/
/**********************************/
/*文件名称：lab3_05.c                 */
/**********************************/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
void  sort(linklist head)
{

}
int main()
{        linklist head;
         head=creatbyqueue();   		/*尾插法建立带头结点的单链表*/
         print(head);    			    /*输出单链表head*/
         sort(head);     				/*排序*/
         print(head);
         delList(head);
         return 0;
}
