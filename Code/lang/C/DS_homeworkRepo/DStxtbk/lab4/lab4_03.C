#include <stdio.h>
#include "stack.h"  /*引入自定义的字符栈结构*/
/**********************/
/* 判断是否为运算符   */
/*********************/
int is_op(char op)
 {
   switch(op)
  { case '+':
    case '-':
    case '*':
    case '/':return 1;
    default:return 0;
    }
 }
/****************************/
/*   判断运算符的优先级     */
/****************************/
int priority(char op)
   {
     switch(op)
       {
          case '(':return 0;
          case '+':
          case '-':return 1;
          case '*':
          case '/':return 2;
	    default: return -1;
        }
  }

/*********************************/
/*中缀表达式，转换为后缀表达式   */
/*********************************/
void postfix(char e[],char f[])
 {seqstack opst;
  int i,j;
  initstack(&opst);
  push(&opst,'\0');
  i=j=0;
  while (e[i]!='\0')
   { if ((e[i]>='0' && e[i]<='9') || e[i]=='.')
           f[j++]=e[i];                     /*数字*/
      else if (e[i]=='(')                /*左括号*/
               push(&opst,e[i]);
           else if (e[i]==')')           /*右括号*/
              { while (stacktop(&opst)!='(')
                        f[j++]=pop(&opst);
                  pop(&opst);            /*'('出栈*/
               }
           else if (is_op(e[i]))         /* '+ ,-, *, /' */
                  {f[j++]=' ';           /*用空格分开两个操作数*/
                   while (priority(stacktop(&opst))>=priority(e[i]))
                       f[j++]=pop(&opst);

                   push(&opst,e[i]);     /*当前元进栈*/
                   }
       i++;  /*处理下一元*/
    }

    while (!stackempty(&opst))
        f[j++]=pop(&opst);
    f[j]='\0';
  }
/****************************************/
/*    将数字字符串转变成数值            */
/****************************************/
float readnumber(char f[],int *i)
  {float x=0.0;
   int k=0;
   while (f[*i]>='0' && f[*i]<='9') /*处理整数部分*/
   {
       x=x*10+(f[*i]-'0');
        (*i)++;
   }
   if (f[*i]=='.') /*处理小数部分*/
       {  (*i)++;
            while (f[*i]>='0' && f[*i]<='9')
                    {   x=x*10+(f[*i]-'0');
                        (*i)++;
                        k++;
                    }
      }
  while (k!=0)
    {       x=x/10.0;
            k=k-1;
    }
    printf("\n*%f*",x);
    return(x);
}

/****************************************/
/*         后缀表达式求值程序           */
/****************************************/
double  evalpost(char f[])
  {  double   obst[50]; /*操作数栈*/
     int i=0,top=-1;
     /*请将本函数补充完整*/




  }

/*
主程序：输入中缀表达式，经转换后输出后缀表达式
*/
int main()
 {
        char e[50],f[50];
        int i,j;
        printf("\n\n请输入中缀表达式:\n");
        gets(e);
        postfix(e,f);
        i=0;
        printf("\n\n对应的后缀表达式为: [");
        while (f[i]!='\0')
                printf("%c",f[i++]);
        printf("]");
        printf("\n\n计算结果为 :");
        printf("\n\n%f",evalpost(f));
        return 0;
}
