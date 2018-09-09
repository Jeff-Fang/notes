/*
编写程序输出以邻接表为存储结构的无向图的各顶点的度。
*/
/**********************************/
/*文件名称：lab8_01.c                 */
/**********************************/
#include "ljb.h"
/* 输出以邻接表为存储结构的无向图g的各顶点的度 */
void degree(LinkedGraph g)
{

}
int main()
{ LinkedGraph g;
  creat(&g,"g11.txt",0);  	/*已知g11.txt中存储了图的信息*/
  printf("\n The graph is:\n");
  print(g);
  degree(g);
  return 0;
}
