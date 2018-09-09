/*
 * postFix.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */

void postFix(char infix[], char postfix[])
{
  int i=0,j=0;
  char operationStack[100];
  int top=0,t;
  operationStack[top]='#';
  top++;

  printf("\npostFix got it!\n");

  while (infix[i]!='#')
  {
    if ( (infix[i] >= '0' && infix[i] <= '9') || infix[i] == '.')
      postfix[j++]=infix[i];
    else if (infix[i] == '(')
    {
      operationStack[top] = infix[i];
      top++;
    }
    else if (infix[i] == ')')
    {
      t=top-1;
      while (operationStack[t]!='(')
      {
        postfix[j++]=operationStack[--top];
        t=top-1;
      }
      top--;
    }
    else if (isOperation(infix[i]))
    {
      postfix[j++]=' ';
      while (priority(operationStack[top-1]) >= priority(infix[i]))
        postfix[j++] = operationStack[--top];
      operationStack[top] = infix[i];
      top++;
    }
    i++;
  }
  while (top) postfix[j++]=operationStack[--top];

  printf("postfix in the function is : %s\n", postfix);
}
