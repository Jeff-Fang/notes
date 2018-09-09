/*
  编写快速模式匹配KMP算法，请将相关函数补充完整。
*/
#define maxsize 100
typedef struct{
      char str[maxsize];
      int length ;
} seqstring;
/*求模式p的next[]值，请将函数补充完整*/
void getnext(seqstring p,int next[])
{

}
/*快速模式匹配算法，请将函数补充完整*/
int kmp(seqstring t,seqstring p,int next[])
{

}
int  main()
 {   seqstring t, p;
     int next[maxsize],pos;
     printf("请输入主串：\n");
     gets(t.str);
     t.length=strlen(t.str);
     printf("请输入模式串：\n");
     gets(p.str);
     p.length=strlen(p.str);
     getnext(p,next);
     pos=kmp(t,p,next);
     printf("\n");
     printf("%d",pos);
     return 0;
}

