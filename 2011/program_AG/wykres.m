clear;
clc;

x=-1:0.01:2;
y=x;

[x y]=meshgrid(x,y);
z=10+x.*sin(40.*x).*cos(10.*x) + y.*sin(30.*y).*cos(20.*y+0.5) - (x-0.2).^2 - (y-0.5).^2;

mesh(x,y,z)

