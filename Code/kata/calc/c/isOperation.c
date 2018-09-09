/*
 * isOperation.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */


int isOperation(char op)
{
  switch (op)
  {
    case '+':
    case '-':
    case '*':
    case '/': return 1;

    default: return 0;
  }
}
