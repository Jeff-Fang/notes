/*
图采用邻接表存储结构，编程对图进行广度优先遍历。
*/
/**********************************/
/*文件名称：lab8_02.c                 */
/**********************************/
#include "ljb.h"
int visited[M];  				/*全局标志向量*/
/*请将本函数补充完整，并进行测试*/
void bfs(LinkedGraph g, int i)
{ /*从顶点i出发广度优先变量图g的连通分量*/

}


/*函数功能：广度优先遍历图g
  函数参数：邻接表g
*/
int BfsTraverse(LinkedGraph g)
{  int i,count=0;
   for (i=0;i<g.n;i++)
       visited[i]=0;     /*初始化标志数组*/

   for (i=0;i<g.n;i++)
       if (!visited[i])  /*vi未访问过*/
       {printf("\n");
        count++;            /*连通分量个数加1*/
        bfs(g,i);
       }
   return count;
 }

int main()
{ 	  LinkedGraph g;
      int count;
      creat(&g,"g11.txt",0);  		/*创建图的邻接表*/
      printf("\n The graph is:\n");
      print(g);
      printf("广度优先遍历序列为：\n");
      count=BfsTraverse(g);     	/*从顶点0出发广度优先遍历图g*/
      printf("\n该图共有%d个连通分量。\n",count);
      return 0;
}


