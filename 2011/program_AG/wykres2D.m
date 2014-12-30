clear;
clc;
x=0:0.001:2;

y=20-6.*sin(32.*x).*cos(7.*x)-30.*(x-1).^2;
plot(x,y)

