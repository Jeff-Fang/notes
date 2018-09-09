#include "sequlist.h"

void sprit(sequence_list *L1,sequence_list *L2,sequence_list *L3)
{
    int i,x=0,y=0;
    for(i=0;i<=L1->size-1;i++){
        if(L1->a[i]%2){
            L2->a[x]=L1->a[i];
            x++;
            L2->size++;
        }
        else if(L1->a[i]%2==0){
            L3->a[y]=L1->a[i];
            y++;
            L3->size++;
        }
    }
}
int main()
{   sequence_list L1,L2,L3;	
    input(&L1);			
    sprit(&L1,&L2,&L3);
    print(&L1);printf("\n");
    print(&L2);printf("\n");
    print(&L3);
}
