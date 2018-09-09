#include "singleList.h" 
linklist insert(linklist head ,datatype x){
    linklist new, find = head, previous = NULL;
    new = (linklist)malloc(sizeof(node));
    new->info = x;
    while(find->info < new->info && find->next!=NULL){
        previous = find;
        find = find->next;
    }
    previous->next = new;
    new->next = find;
    return head;
}

int main() {
    datatype x;
    linklist head;
    printf("Pls typein a rise list of integer:\n");
    head = creatbyqueue();
    print(head);
    printf("The integer you want to insert: ");
    scanf("%d",&x);
    head = insert (head, x);
    print(head);
    delList(head);
    return 0;
}
