/*
    图采用邻接表存储结构，编程对图进行深度优先遍历。
*/

#include "ljb.h"
int visited[M];
/*请将本函数补充完整，并进行测试*/

void dfs(LinkedGraph g,int i)
{   /*从顶点i开始深度优先遍历图的连通分量*/
    EdgeNode *p;
  	printf("visit vertex: %c \n",g.adjlist[i].vertex);/*访问顶点i*/
  	visited[i]=1;
  	p=g.adjlist[i].FirstEdge;
  	while (p)                 /*从p的邻接点出发进行深度优先搜索*/
    {

    }
}
/*函数功能：深度优先遍历图
  函数参数：图的邻接表g
*/
void DfsTraverse(LinkedGraph g)
{  int i;
   for (i=0;i<g.n;i++)
       visited[i]=0;     /*初始化标志数组*/
   for (i=0;i<g.n;i++)
       if (!visited[i])  /*vi未访问过*/
            dfs(g,i);
 }

int main()
{ LinkedGraph g;
  creat(&g,"g11.txt",0);  			/*创建图的邻接表*/
  printf("\n The graph is:\n");
  print(g);
  printf("深度优先遍历序列为：\n");
  DfsTraverse(g);    				/*从顶点0开始深度优先遍历图无向图g*/
}
