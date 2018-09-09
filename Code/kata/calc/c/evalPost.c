/*
 * evalPost.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */

double evalPost(char f[])
{
  double opStack[100];
  int top=0;
  int i=0;
  double x1,x2;

  printf("evalPost got it!");


  while (f[i]!='#')
  {
    if (f[i]>='0' && f[i]<='9')
    {
      opStack[top] = readNumber(f,&i);
      top++;
    }


    else if (f[i]=='+')
    {
      x2=opStack[--top];
      x1=opStack[--top];
      opStack[top]=x1+x2;
      top++;i++;
    }
    else if (f[i]=='-')
    {
      x2=opStack[--top];
      x1=opStack[--top];
      opStack[top]=x1 - x2;
      top++;i++;
    }
    else if (f[i]=='*')
    {
      x2=opStack[--top];
      x1=opStack[--top];
      opStack[top]=x1 * x2;
      top++;i++;
    }
    else if (f[i]=='/')
    {
      x2=opStack[--top];
      x1=opStack[--top];
      opStack[top]=x1 / x2;
      top++;i++;
    }

  }

  printf("evalPost completed!");
  printf("%f\n", opStack[0]);

  return opStack[0];
}
