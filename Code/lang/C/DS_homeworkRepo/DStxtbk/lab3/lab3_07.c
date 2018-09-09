/*
设计一个算法linklist interSection(linklist L1,linklist L2)，
求两个单链表表示的集合L1和L2的交集，并将结果用一个新的带头
结点的单链表保存并返回表头地址。
*/
/**********************************/
/*文件名称：lab3_07.c                 */
/**********************************/
#include "slnklist.h"
/*请将本函数补充完整，并进行测试*/
linklist   interSection(linklist L1, linklist L2)
{

}
int main()
{
 linklist h1,h2,h3;
 h1=creatbyqueue();           /*尾插法建立单链表,输入时请勿输入重复数据*/
 h2=creatbyqueue();
 print(h1);                   /*输出单链表h1*/
 print(h2);
 h3=interSection(h1,h2);      /* 求h1和h2的交集*/
 print(h3);
 delList(h1);
 delList(h2);
 delList(h3);
 return 0;
}
