#include  <stdio.h>
#include  <stdlib.h>
/**********************************/
/*Ë³Ðò±íµÄÍ·ÎÄ¼þ£¬ÎÄ¼þÃûsequlist.h*/
/**********************************/
#define MAXSIZE 100
typedef int datatype;
typedef struct{
    datatype a[MAXSIZE];
    int size;
}sequence_list;

/**********************************/
/*º¯ÊýÃû³Æ£ºinitseqlist()         */
/*º¯Êý¹¦ÄÜ£º³õÊ¼»¯Ë³Ðò±í          */
/**********************************/
void initseqlist(sequence_list *L)
{	L->size=0;
}
/**********************************/
/*º¯ÊýÃû³Æ£ºinput()               */
/*º¯Êý¹¦ÄÜ£ºÊäÈëË³Ðò±í            */
/**********************************/
void input(sequence_list *L)
{  datatype x;
    initseqlist(L);
    printf("ÇëÊäÈëÒ»×éÊý¾Ý£¬ÒÔ0×öÎª½áÊø·û£º\n");
    scanf("%d",&x);
    while (x)
   	{	L->a[L->size++]=x;
        scanf("%d",&x);
    }
}




/**********************************/
/*º¯ÊýÃû³Æ£ºinputfromfile()       */
/*º¯Êý¹¦ÄÜ£º´ÓÎÄ¼þÊäÈëË³Ðò±í      */
/**********************************/
void inputfromfile(sequence_list *L,char *f)
{  int i,x;
    FILE *fp=fopen(f,"r");
    L->size=0;
    if (fp)
    {   while ( ! feof(fp))
    {
        fscanf(fp,"%d",&L->a[L->size++]);
    }
        fclose(fp);
    }
}
/**********************************/
/*º¯ÊýÃû³Æ£ºprint()               */
/*º¯Êý¹¦ÄÜ£ºÊä³öË³Ðò±í            */
/**********************************/
void print(sequence_list *L)
{   int i;
    for (i=0;i<L->size;i++)
    {    printf("%5d",L->a[i]);
        if ((i+1)%10==0) printf("\n");
    }
    printf("\n");
}
