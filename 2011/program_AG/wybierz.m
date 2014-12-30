function [do_rozrodu, ktore] = wybierz(osobniki, ocena)
N=size(osobniki,2);
M=size(osobniki,3);

do_rozrodu=zeros(10,M);

ktore=1:10;

dystrybuanta=zeros(N,1);

suma_ocen=sum(ocena);

dystrybuanta(1)=ocena(1)/suma_ocen;

osobniki_i_ocena=zeros(N,M+2);
for i=1:N
    
    for j=1:M
    osobniki_i_ocena(i,j)=osobniki(1,i,j);
    end
    osobniki_i_ocena(i,j+1)=ocena(i);
    osobniki_i_ocena(i,j+2)=i;
    
end


wylosowani=rand(8,1);

posortowane=sortrows(osobniki_i_ocena,-(M+1));

for i=1:10
    
    do_rozrodu(i,:)=posortowane(i,1:M);
    ktore(i)=posortowane(i,M+2);
    
    
end




end