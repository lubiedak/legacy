function [dystanse] = generuj_macierz_dystansow(xy)
n=size(xy,1);
dystanse=zeros(n,n);

for i=1:n
    for j=1:n
        dystanse(i,j)=((xy(i,1)-xy(j,1))^2+(xy(i,2)-xy(j,2))^2)^(0.5); %macierz odleglosci
    end
end
end