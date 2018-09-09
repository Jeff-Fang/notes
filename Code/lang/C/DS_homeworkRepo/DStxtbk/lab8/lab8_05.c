/***************************************************/
/*          Dijkstra单源最短路径算法               */
/***************************************************/
#include "ljjz.h"   /*引入邻接矩阵创建程序*/
typedef enum{FALSE,TRUE} boolean;/*false为0,true为1*/
typedef int dist[M];  /* 距离向量类型*/
typedef int path[M];  /* 路径类型*/

/*函数功能：Dijkstra算法求解单源最短路径
函数参数：图的邻接矩阵g;源点v0;路径向量p；距离向量d
*/
void dijkstra(Mgraph g,int v0,path p,dist d)
 { boolean final[M]; /*表示当前元素是否已求出最短路径*/
   int i,k,j,v,min,x;

   /*  第1步  初始化集合S与距离向量d */

   /* 第2步  依次找出n-1个结点加入S中   */


   /*第3步 修改S与V-S中各结点的距离*/



   }
/*函数功能：输出有向图的最短路径
函数参数：邻接矩阵g；路径向量p；距离向量d
*/
void print_gpd(Mgraph g,path p,dist d)
 {
   int st[M],i,pre,top=-1;
   for (i=0;i<g.n;i++)
    { printf("\nDistancd: %7d , path:" ,d[i]);
      st[++top]=i;
      pre=p[i];
      while (pre!=-1)   /*从第i个顶点开始向前搜索最短路径上的顶点*/
        { st[++top]=pre;
          pre=p[pre];
         }
      while (top>0)
     printf("%2d",st[top--]);
    }
 }
/*---------- 主程序 ------------*/
int main()
 { Mgraph g;   /* 有向图 */
   path p;     /* 路径向量 */
   dist d;     /* 最短路径向量 */
   int v0;
   creat(&g,"g21.txt",1); /*假设图8.21所示的有向网G21的输入文件为g21.txt */
   print(g);        /*输出图的邻接矩阵*/
   printf("\n");
   printf("请输入源点编号：");
   scanf("%d",&v0);       /*输入源点*/
   dijkstra(g,v0,p,d);   /*求v0到其他各点的最短距离*/
   /*输出V0到其它各点的路径信息及距离*/
   print_gpd(g,p,d);

    return 0;
  }
