function [zera_jedynki,polaczone] = laczenie(ktore,N,x,wczesniejsze,pierwsza,druga)

ile_opcji=size(pierwsza,1);
ilosc_tirow=size(druga,1);
polaczone=zeros(2,1+ktore);
zera_jedynki=zeros(2,N);
next=0;
if ktore==2
  for i=1:ile_opcji
  
   for j=i+1:ilosc_tirow
      a=pierwsza(i,:)+druga(j,:);
      if max(a)==1
         next=next+1;
         polaczone(next,:)=[wczesniejsze(i,1)+x(j) wczesniejsze(i,2:ktore), j];
         zera_jedynki(next,:)=a;
      end
            
   end
    
  end

else
for i=1:ile_opcji
  
   for j=wczesniejsze(i,ktore)+1:ilosc_tirow
      a=pierwsza(i,:)+druga(j,:);
      if max(a)==1
         next=next+1;
         polaczone(next,:)=[wczesniejsze(i,1)+x(j) wczesniejsze(i,2:ktore), j];
         zera_jedynki(next,:)=a;
      end
            
   end
    
end
end
end