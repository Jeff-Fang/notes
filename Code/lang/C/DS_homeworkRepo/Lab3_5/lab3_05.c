/*
已知线性表存储在带头结点的单链表head中，请设计算法函数void sort(linklist head)，将head中的结点按结点值升序排列。
*/
#include "slnklist.h"
void  sort(linklist head)
{   linklist compare, bubble;
    int x;
    for(compare = head->next; compare != NULL; compare = compare->next){
        for(bubble = compare->next; bubble != NULL; bubble = bubble->next){
            if( compare->info > bubble->info){
                x = compare->info;
                compare->info = bubble->info;
                bubble->info = x; 
            }
        }
    }
}

int main()
{        linklist head;
         head=creatbyqueue();   		/*尾插法建立带头结点的单链表*/
         print(head);    			    /*输出单链表head*/
         sort(head);     				/*排序*/
         print(head);
         delList(head);
         return 0;
}
