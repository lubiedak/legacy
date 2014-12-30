function [] = narysuj_mape_miast(xy,zapotrzebowanie)
n=size(xy,1);
plot(xy(:,1),xy(:,2),'.') %wykres miast

for k=1:n-1
    text(xy(k,1)-0.15,xy(k,2)+0.5,num2str(k)) %naniesiona numeracja miast
    %w=round(zapotrzebowanie(k)*100)/100;
    %text(xy(k,1)+0.15,xy(k,2)-0.1,num2str(w)) %naniesiona numeracja miast 
end

text(xy(k+1,1)-0.8,xy(k+1,2),num2str(k+1))
end