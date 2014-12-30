clear;
clc;
N=18;
min=20;
max=30;
max_miast=4;
zapotrzebowanie=[12,14,11,6,6,11,5,7,14,15,12,13,5,12,13,10,14,15]';
opcje=zeros(349,max_miast);
next=0;
poczatek=rem(now,1);
for i=3:2^N-2^(N-max_miast)
      bin=de2bi(i,N);
      ilemiast=sum(bin);
      
   if (ilemiast>1 && ilemiast<=max_miast)
      
      wypelnienie=bin*zapotrzebowanie;
      
      if(wypelnienie>min && wypelnienie<max)
          next=next+1;
          
   w=1;
     for k=1:N
        if(bin(k)==1)
           opcje(next,w)=k;
               
               w=w+1;
        end
     end
     
                            
      end
   end
end
koniec=rem(now,1);
czas=24*3600*(koniec-poczatek);
czas