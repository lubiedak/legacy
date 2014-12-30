function [kolejnosc,dystans] = najkrotsza(start,cities,m_odleglosci)

ile=length(cities);
perm=[4 3 2 1; 4 3 1 2; 4 2 3 1; 4 2 1 3; 4 1 2 3; 4 1 3 2; 3 4 2 1;
    3 4 1 2; 3 2 4 1; 3 1 4 2; 2 3 4 1; 2 4 3 1];
per=[1 2 3; 1 3 2; 2 1 3];
a=zeros(3,4);
b=zeros(12,5);
switch ile    
    case 2
    dystans=odleglosc(start,cities,m_odleglosci);
    kolejnosc=cities;   
    
    case 3
    miasta=cities;
    for i=1:3    
    a(i,1)=odleglosc(start,miasta(per(i,:)),m_odleglosci);
    a(i,2:4)=miasta(per(i,:));
    end
    
    aa=sortrows(a,1);
    
    kolejnosc=aa(1,2:4);
    dystans=aa(1,1);
    
    
    case 4
    miasta=cities;
    for i=1:12    
    b(i,1)=odleglosc(start,miasta(perm(i,:)),m_odleglosci);
    b(i,2:5)=miasta(perm(i,:));
    end
    bb=sortrows(b,1);
    
    kolejnosc=bb(1,2:5);
    dystans=bb(1,1);
    
end