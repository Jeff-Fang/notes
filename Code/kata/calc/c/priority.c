/*
 * priority.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */


int priority(char op)
{
  switch(op)
  {
    case '#': return -1;
    case '(': return 0;
    case '+':
    case '-': return 1;
    case '*':
    case '/': return 2;

    default: return -1;
  }
}
