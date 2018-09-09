/*
请设计基于链表的基数排序函数void radixSort(linklist head)，对带头结点的整型非负单链表进行升序排序。
并测试在不同数据规模下的排序效率。(注：链表中的最大整数为500000)
*/
#include "slnklist.h"
struct  node2
{
    linklist front,rear;
};
#define N 1000     /*N为数据量大小，因data1.txt中只有50万个数，所以自行设定N值时需让N<=500000*/

/*请将本函数补充完整，并进行测试*/

void radixSort(linklist head)
{
    struct node2 q[10];/*队列*/

}
int  main()
{
  linklist head;
  printf("数据初始化...\n");
  head=creatLink("data1.txt",N);      /*从data1.txt中读入N个整数存入数组a，n为实际读入的数据个数*/
  printf("数据排序中...\n");
  radixSort(head);
  writetofile(head,"out.txt");        /*排序结果保存在out.txt中*/
  delList(head);
  return 0;
}
