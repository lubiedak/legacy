function [opcje_1_tir] = generuj_opcje_1_tir(dystanse)

load('zmienne');
opcje_1_tir=zeros(10,max_miast+2+N);
next=0; %zmienna pomocnicza, s³u¿¹ca do wypelniania wszystkich mo¿liwych opcji dostawy

   for i=1:2^N-2^(N-max_miast) %pierwotna wersja 2^a-1
      bin=de2bi(i,N);
      ilemiast=sum(bin);
      
   if (ilemiast>1 && ilemiast<=max_miast)
      
      wypelnienie=bin*zapotrzebowanie;
      
      if(wypelnienie>min_zaladunek && wypelnienie<max_zaladunek)
      
          [kolejnosc,ilekm]=najkrotsza(N+1,find(bin),dystanse);
          
      next=next+1;
      switch ilemiast
          case 2
      opcje_1_tir(next,:)=[kolejnosc 0 0 wypelnienie ilekm bin];
          case 3
      opcje_1_tir(next,:)=[kolejnosc 0 wypelnienie ilekm bin];
          case 4
      opcje_1_tir(next,:)=[kolejnosc wypelnienie ilekm bin];
       
      end
      end
   end
       
   end    
opcje_1_tir=sortrows(opcje_1_tir,6);

end