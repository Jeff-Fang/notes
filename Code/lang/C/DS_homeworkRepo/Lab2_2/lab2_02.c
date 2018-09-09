
#include "singleListWithoutHeadNode.h"

linklist reverse1(linklist head){
    linklist read = head,privious = NULL, result = NULL;
    do{
       result=(linklist)malloc(sizeof(node));
       result->info = read->info;
       result->next = privious;
       privious = result;
       read = read->next;
    }while(read!=NULL);
    delList(head);
    return result;
}

void reverse2(linklist *head){
    *head = reverse1(*head);
}

int main(){
//    datatype x;
    linklist head;
    head = creatbystack();
    print(head);
    head = reverse1(head);
    print(head);
    reverse2(&head);
    print(head);
    delList(head);
    return 0;
}

