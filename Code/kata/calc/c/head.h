/*
 * head.h
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */
#include <stdio.h>

#ifndef HEAD_H_
#define HEAD_H_

double readNumber(char f[], int *i);
int isOperation(char op);
int priority(char op);
void postFix(char infix[], char postfix[]);
double evalPost(char f[]);


#endif /* HEAD_H_ */
