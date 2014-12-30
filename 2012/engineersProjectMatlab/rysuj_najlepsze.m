function [] = rysuj_najlepsze(magazyn,xy,opcje)

ile_tsp=size(opcje,1);
punkty=opcje(:,1:4);
kolorki=char('blue','green','red','cyan','magenta','yellow','black');

for i=1:ile_tsp
    
    linia(xy,magazyn,punkty(i,1),kolorki(i))
    kolejne=1;
while(punkty(i,kolejne+1)>0)
    
   linia(xy,punkty(i,kolejne),punkty(i,kolejne+1),kolorki(i))
   kolejne=kolejne+1;
   
   if kolejne==4
       break
   end
end
linia(xy,magazyn,punkty(i,kolejne),kolorki(i))
    
end

end