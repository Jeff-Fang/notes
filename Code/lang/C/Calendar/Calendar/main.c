//
//  main.c
//  Exercise_C
//
//  Created by Jeff on 8/22/14.
//  Copyright (c) 2014 Swordx. All rights reserved.
//

#include <stdio.h>

//lean() judges a year a lean year or not
int lean(int year){
    if((year%4==0&&year%100!=0)||(year%400==0)) return 1;
    else return 0;
}

//yeardays() counts how many days a year to 2014 new year's day
int yeardays(int year){
    int d=0,y;
    if (year==2014) {
        return 0;
    }
    else if (year<2014){
        for (y=year; y!=2014; y=y+1) {
            if (lean(y)) {
                d-=366;
            }
            else d-=365;
        }
    }
    else {
        for (y=year-1; y>=2014; y=y-1) {
            if (lean(y)) {
                d+=366;
            }
            else d+=365;
        }
    }
    return d;
}

//weekday() caculates the weekday according to Jan 1,2014
int weekday(int count){
    int n;
    n=count%7+4;
    if (n>7) {
        n=n-7;
    }
    if (n<1) {
        n=n+7;
    }
    return n;
}

//monthdays() counts how many days from new year to a particular month.
int monthdays(int month,int year){
    int n=0,x;
    int monthdays[13]={0,31,28,31,30,31,30,31,31,30,31,30,31};
    if(lean(year)==1)monthdays[2]=29;
    
    
    for (x=1; x<=month; x++) {
        n=n+monthdays[x-1];
    }
    return n;
}

void printcal(int n){
    if (n==0) {
        printf(" ");
    }
    else if (n>100&&n<123){
        switch (n) {
            case 101:
                printf("Jan");
                break;
            case 102:
                printf("Feb");
                break;
            case 103:
                printf("Mar");
                break;
            case 104:
                printf("Apr");
                break;
            case 105:
                printf("May");
                break;
            case 106:
                printf("Jun");
                break;
            case 107:
                printf("Jul");
                break;
            case 108:
                printf("Aug");
                break;
            case 109:
                printf("Sep");
                break;
            case 110:
                printf("Oct");
                break;
            case 111:
                printf("Nov");
                break;
            case 112:
                printf("Dec");
                break;
            default: printf("   ");
                break;
        }
    }
    else printf("%d",n);
}


int main(int argc, const char * argv[]){
    //define the var
    int line,row,date,i=0,j=0,k=0,x=0,list=0,groupe=0,l;
    int year,countdate,whichday;
    int cal[72][7];
    //input a year
    printf("Which year do you want to search?\n");
    scanf("%d",&year);
    printf("How many months do you want to display in a line?\n");
    scanf("%d",&x);
    
    
    //dealling with the month lists
    //jan
    date=1;
    countdate=yeardays(year)+monthdays(1, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+0*6][row]=0;
            }
            else if(date<=31){
                cal[line+0*6][row]=date;
                date++;
            }
            else cal[line+0*6][row]=0;
        }
    }
    //feb
    date=1;
    countdate=yeardays(year)+monthdays(2, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+1*6][row]=0;
            }
            else if(date<=monthdays(3, year)-31){
                cal[line+1*6][row]=date;
                date++;
            }
            else cal[line+1*6][row]=0;
        }
    }
    //mar
    date=1;
    countdate=yeardays(year)+monthdays(3, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+2*6][row]=0;
            }
            else if(date<=31){
                cal[line+2*6][row]=date;
                date++;
            }
            else cal[line+2*6][row]=0;
        }
    }
    //apr
    date=1;
    countdate=yeardays(year)+monthdays(4, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+3*6][row]=0;
            }
            else if(date<=30){
                cal[line+3*6][row]=date;
                date++;
            }
            else cal[line+3*6][row]=0;
        }
    }
    //may
    date=1;
    countdate=yeardays(year)+monthdays(5, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+4*6][row]=0;
            }
            else if(date<=31){
                cal[line+4*6][row]=date;
                date++;
            }
            else cal[line+4*6][row]=0;
        }
    }
    //jun
    date=1;
    countdate=yeardays(year)+monthdays(6, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+5*6][row]=0;
            }
            else if(date<=30){
                cal[line+5*6][row]=date;
                date++;
            }
            else cal[line+5*6][row]=0;
        }
    }
    //jul
    date=1;
    countdate=yeardays(year)+monthdays(7, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+6*6][row]=0;
            }
            else if(date<=31){
                cal[line+6*6][row]=date;
                date++;
            }
            else cal[line+6*6][row]=0;
        }
    }
    //aug
    date=1;
    countdate=yeardays(year)+monthdays(8, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+7*6][row]=0;
            }
            else if(date<=31){
                cal[line+7*6][row]=date;
                date++;
            }
            else cal[line+7*6][row]=0;
        }
    }
    //sep
    date=1;
    countdate=yeardays(year)+monthdays(9, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+8*6][row]=0;
            }
            else if(date<=30){
                cal[line+6*8][row]=date;
                date++;
            }
            else cal[line+6*8][row]=0;
        }
    }
    //oct
    date=1;
    countdate=yeardays(year)+monthdays(10, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+6*9][row]=0;
            }
            else if(date<=31){
                cal[line+6*9][row]=date;
                date++;
            }
            else cal[line+6*9][row]=0;
        }
    }
    //nov
    date=1;
    countdate=yeardays(year)+monthdays(11, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+6*10][row]=0;
            }
            else if(date<=30){
                cal[line+6*10][row]=date;
                date++;
            }
            else cal[line+6*10][row]=0;
        }
    }
    //dec
    date=1;
    countdate=yeardays(year)+monthdays(12, year);
    whichday=weekday(countdate);
    for (line=0; line<6; line++) {
        for (row=0;row<7 ; row++) {
            if (row<whichday-1&&line==0){
                cal[line+6*11][row]=0;
            }
            else if(date<=31){
                cal[line+6*11][row]=date;
                date++;
            }
            else cal[line+6*11][row]=0;
        }
    }
    //print the calendar
    printf("----------------------------%d----------------------------\n",year);
    for (groupe=0; groupe*x<12; groupe++) {
        for (k=(groupe*x*6); k<72; k++) /*打印日历一group*/{
            for (i=k; list<x; i+=6)/*打印日历一行*/ {
                if (i%(6*x)==0) {
                    for (date=0; date<x; date++) {
                        printcal(101+groupe*x+date);
                        printf("                     ");
                    }
                    printf("\n");
                    if ((groupe+1)*x>12) {
                        l=12%x;
                    }
                    else l=x;
                    for (date=0; date<l; date++) {
                        printf("Su Mo Tu We Th Fr Sa    ");
                    }
                    printf("\n");
                }
                if (i>=72) {
                    break;
                }
                for (j=0; j<7; j++)/*打印某月一行*/ {
                    
                    if (cal[i][j]<10)printf(" ");
                    printcal(cal[i][j]);printcal(0);
                }
                printf("\t");
                list++;
            }
            list=0;
            printf("\n");
            if (k%6==5) {break;}
        }
        printf("\n");
    }
    
    // insert code above
    printf("Type in any keys to exit");
    scanf("%d",&i);
    return 0;
}



































