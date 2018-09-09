/*
 * readNumber.c
 *
 *  Created on: Apr 12, 2015
 *      Author: phiverse
 */

double readNumber(char f[], int *i)
{
  double x=0.0;
  int k=0;
  while( f[*i]>='0' && f[*i]<='9')
  {
    x=x*10+(f[*i]-'0');
    (*i)++;
  }
  if(f[*i]=='.')
  {
    (*i)++;
    while( f[*i]>='0' && f[*i]<='9')
    {
      x=x*10+(f[*i]-'0');
      (*i)++;
      k++;
    }
  }
  while (k!=0)
  {
    x=x/10.0;
    k=k-1;
  }
  return x;
}
