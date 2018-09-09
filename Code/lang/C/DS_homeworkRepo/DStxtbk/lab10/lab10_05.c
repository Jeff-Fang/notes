/*
请设计筛选函数void sift(int a[],int k,int n)，对a[k] 进行筛选，
并利用其设计堆排序算法函数void heapSort(int a[],int n)，
对a[1]..a[n]进行升序排序。并测试在不同数据规模下的排序效率。（详见lab10_05.c）
*/
#include "Arrayio.h"
#define N 10000     /*N为数据量大小，因data1.txt中只有50万个数，所以自行设定N值时需让N<=500000*/

/*请将本函数补充完整，并进行测试*/
void sift(int a[],int k,int n)
{
      int i,j,finished;
      i=k;j=2*i;
      a[0]=a[k];
      finished=0;
      while((j<=n)&&(!finished))
      {
        if((j<n)&&(a[j+1]>a[j]))
             j++;
        if(a[0]>=a[j])
             finished=1;
        else
        {
          a[i]=a[j];
          i=j;j=2*j;
        }
      }
      a[i]=a[0];
    }

void heapSort(int a[],int n)
{
    int i;
    for (i=n/2;i>=1;i--)
        sift(a,i,n);
    for (i=n;i>1;i--)
        {
            a[0]=a[i];
            a[i]=a[1];
            a[1]=a[0];
            sift(a,1,i-1);
        }
}

int  main()
{
  int a[N+1],n;                     /*数据存储在a[1]...a[N]中*/
  printf("数据初始化...\n");
  n=readData(a,N,"data1.txt");      /*从data1.txt中读入N个整数存入数组a，n为实际读入的数据个数*/
  printf("%d个数据排序中...\n",n);
  heapSort(a,n);
  saveData(a,n,"out.txt");          /*排序结果存放在out.txt文件中*/
  printf("排序结束，排序结果保存在out.txt文件中。\n");
  return 0;
}
