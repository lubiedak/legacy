#include <cstdlib>
#include <iostream>
#include <math.h>
#include <time.h>

using namespace std;

int main(int argc, char *argv[])
{
    int N,min,max,max_miast,next;
    double dwojka;
    N=18;
    min=20;
    max=30;
    max_miast=4;
    next=0;
    int zapotrzebowanie[18]={12,14,11,6,6,11,5,7,14,15,12,13,5,12,13,10,14,15};
    int a=0,licz;
    
    int ile_miast;
    int zaladunek,i,j,k,w;
    int opcje[349][4];
    int bin[18];
    
    time_t start,end;
    double dif;
    time (&start);
    clock_t poczatek,koniec;
    assert((poczatek = clock())!=-1);
    
    for(int dd=0;dd<1000;dd++){
            next=0;
            a=0;
   int bin[18]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    dwojka=pow(2,N)-pow(2,N-max_miast);
    for(i=3;i<dwojka;i++){
            a=i;
            licz=0;
            ile_miast=0;
            while (a>0){
                  bin[licz]=a%2;
                  
                  if(a%2==1){
                             ile_miast++;
                             }
                  a=a/2;
                  licz++;
                  }
                  
            if(ile_miast>1 && ile_miast<=max_miast)
            {               
                    zaladunek=0;
                    for(j=0;j<N;j++)
                    {
                       if(bin[j]==1)
                                    {
                         zaladunek=zaladunek+zapotrzebowanie[j];
                                    }
                                   }
                                   
                                   
                    if(zaladunek>min && zaladunek<max)
                                            { w=0;
                                            for(k=0;k<N;k++){
                                                           if(bin[k]==1){
                                                                         opcje[next][w]=k;
                                                                        // printf("%d ",k);
                                                                         w++;
                                                                         }
                                            
                                                           }
                                                         //  printf("\n");
                                                           next++;
                                            }
                                            
                           
                           }
            
            
            }
           // printf("%d \n",next);
}
time (&end);
koniec=clock();
dif = difftime (end,start);
printf("%.5lf \n",dif);
printf("%lf \n",(koniec-poczatek)/CLOCKS_PER_SEC);
    system("PAUSE");
    return EXIT_SUCCESS;
}
