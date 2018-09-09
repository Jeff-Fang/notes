/*********************************************/
/*           Prim求解最小生成树算法          */
/*********************************************/
#include "ljjz.h"

typedef struct edgedata  /*用于保存最小生成树的边类型定义*/
       { int beg,en;     /*beg,en是边顶点序号*/
         int length;     /*边长*/
       }edge;

/*函数功能：prim算法构造最小生成树
函数参数：图的邻接矩阵g;边向量edge
*/
void prim(Mgraph g, edge tree[M-1])
{  edge x;
   int d,min,j,k,s,v;

   /* 建立初始入选点，并初始化生成树边集tree*/



   /*依次求当前(第k条）最小两栖边，并加入TE*/





   /*由于新顶点v的加入，修改两栖边的基本信息*/



   /*输出最小生成树*/
    printf("\n最小生成树是:\n");/*输出最小生成树*/
    for (j=0;j<=g.n-2;j++)
        printf("\n%c---%c  %d\n",g.vexs[tree[j].beg],g.vexs[tree[j].en],tree[j].length);
    printf("\n最小生成树的根是： %c\n", g.vexs[0]);
 }

int  main()
  {
   Mgraph g;
   edge  tree[M-1];  /*用于存放最小生成树的M-1条边*/
   creat(&g,"g.txt",0);  /*创建无向图的邻接矩阵*/
   prim(g,tree);        /*求解图的最小生成树*/
   return 0;

  }

