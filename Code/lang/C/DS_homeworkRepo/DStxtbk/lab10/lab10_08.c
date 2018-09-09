/*
请设计归并排序算法函数void mergeSort(int a[],int n)，对a[1]..a[n]进行升序排序。
并测试在不同数据规模下的排序效率。
*/
#include "Arrayio.h"
#define N 10000     /*N为数据量大小，因data1.txt中只有50万个数，所以自行设定N值时需让N<=500000*/

/*请将本函数补充完整，并进行测试*/

void merge(int a[],int u,int m,int v)
{ /*将有序段a[u..m],a[m+1..v]归并到a[u..v]*/

}


/*----一趟归并------*/
 int  mergepass(int a[],int n,int len)
  { /*对a[1..n]进行长度为len的一趟并归*/


  }

/*----归并排序------*/
void mergeSort(int a[],int n)
{

}

/*归并排序的递归实现*/
void mergeSortdc(int a[],int low,int high)
{

}

int  main()
{
  int a[N+1],n;                     /*数据存储在a[1]...a[N]中*/
  printf("数据初始化...\n");
  n=readData(a,N,"data1.txt");      /*从data1.txt中读入N个整数存入数组a，n为实际读入的数据个数*/
  printf("%d个数据排序中...\n",n);
  mergeSort(a,n);                   /* 或调用mergeSortdc(a,1,n); */
  saveData(a,n,"out.txt");          /*排序结果存放在out.txt文件中*/
  printf("排序结束，排序结果保存在out.txt文件中。\n");
  return 0;
}
