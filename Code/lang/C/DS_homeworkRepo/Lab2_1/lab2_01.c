
#include "singleListWithoutHeadNode.h"

linklist delx(linklist head, datatype x){
    linklist find=head, previous=NULL;
    do{
        if(find->info==x){
            previous->next=find->next;
            return head;
    }

        else {
            previous=find;
            find=find->next;
        }
    }while (find!=NULL);
    printf("Can't find!");
    return NULL;
}


int main(int argc, const char * argv[]) {
    datatype x;
    linklist head;
    head=crearbyqueue();
    print(head);
    printf("pls insert the value to be deleted");
    scanf("%d",&x);
    head=delx(head,x);
    print(head);
    delList(head);
    return 0;
}
