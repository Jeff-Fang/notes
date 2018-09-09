
#include "slnklist.h"

linklist delallx(linklist head,int x)
{   linklist p = head;
    linklist pre = p ;
    int deleted = 0 ;
    if (head->info == x) head = head->next;
    else {
        for( p = head->next ; !deleted ; p = p->next){
            if (p->info == x){
                pre->next = p->next;
                deleted = 1;
            }
            pre = p ;
        }
    }
    return head;
}
int main()
{   datatype x;
    linklist head;
    head=creatbyqueue();
    print(head);
    printf("Which number do you want to delete：");
    scanf("%d",&x);
    head=delallx(head,x);
    print(head);
    delList(head);
    return 0;
}
