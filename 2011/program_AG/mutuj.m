function [zmutowana] = mutuj(populacja)
N=size(populacja,1);
M=size(populacja,2);

zmutowana=populacja; %to powinno byc uogólnione

pp_mutacji=0.12;

for i=1:N
    for j=1:M
losowa=rand(1);

if losowa<pp_mutacji
    if populacja(i,j)==1
        populacja(i,j)=0;
    else
        populacja(i,j)=1;
    end
end
    end
end


end