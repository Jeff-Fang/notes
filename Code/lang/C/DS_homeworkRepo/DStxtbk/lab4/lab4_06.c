/*
字符串采用带头结点的链表存储，编写函数linkstring index(linkstring s, linkstring t)，
查找子串t在主串s中第一次出现的位置，若匹配不成功，则返回NULL。
*/

#include "linkstring.h"
/*请将本函数补充完整，并进行测试*/
linkstring index(linkstring  s, linkstring t)
{

}
int main()
{   linkstring s,t,p=NULL;
    s=creat();                  /*建立主串链表*/
    t=creat();			        /*建立子串链表*/
    print(s);
    print(t);
    p=index(s,t);
    if(p)
            printf("匹配成功，首次匹配成功的位置结点值为%c\n",p->data);
    else
            printf("匹配不成功！\n");
    delList(s);
    delList(t);
    return 0;
}
