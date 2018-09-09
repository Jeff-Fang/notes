/*
 * Completed
字符串采用带头结点的链表存储，设计算法函数void delstring(linkstring s, int i,int len)
在字符串s中删除从第i个位置开始，长度为len的子串。
*/
/**********************************/
/*文件名称：lab4_05.c                 */
/**********************************/
#include "linkstring.h"
/*请将本函数补充完整，并进行测试*/
void delstring(linkstring  s, int i, int len)
{   linkstring fromhere = s , tohere = NULL;
    int x;
    for ( x = 0 ; x < i-1 ; x++ )
        fromhere = fromhere->next;
    tohere = fromhere->next;
    for ( x = i ; x < len ; x++ )
        tohere = tohere->next;
    fromhere->next = tohere->next;
}
int main()
{   linkstring str;
    str=creat();            /*建字符串链表*/
    print(str);
    delstring(str,2,3);     /*测试，从第2个位置删除长度为3的子串,请自行构造不同的测试用例  */
    print(str);               /*输出*/
    delList(str);
    return 0;
}
