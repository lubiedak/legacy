function [ oceny ] = ocen_osobniki( osobniki )
N=size(osobniki,2);
M=size(osobniki,3);

oceny=1:N;

for i=1:N
   x=bin2dec(num2str(osobniki(1,i,:)))/(2^M-1);
   oceny(i)=20-6*sin(32*x)*cos(7*x)-17*(x-1)^2;
end

end

