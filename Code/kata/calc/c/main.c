/*
 * main.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */

#include "head.h"



int main()
{
	char infix[100], postfix[100];
	double value;

	printf("Your math expression:\n");
	scanf("%s", infix);
	printf("input completed!%s\n",infix);
	postFix(infix,postfix);
	printf("postFix completed!%s\n", postfix);
	value = evalPost(postfix);

	printf("after evalPost, %s\n", postfix);

	printf("%s", infix);
	printf(" = ");
	printf("%f", value);
	printf("\nover");

	return 0;
}
