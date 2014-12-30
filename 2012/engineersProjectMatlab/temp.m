
%clear;
%clc;
N=12;             % N liczba odwiedzanych miast

min_zaladunek=19; %minimalny za³adunek samochodu
max_zaladunek=28; %maksymalny za³adunek samochodu

max_miast=4;          %maksymalna liczba miast, ktore mo¿e obslugiwac komiwojazer
max_dystans=500;      %maksymalna dlugosc trasy samochodu 

%xy=20*rand(N+1,2);    % losowanie wspolrzednych miast 
%xy(N+1,:)=[10 0];      %wspolrzedna magazynu

%zapotrzebowanie=5+9.*rand(N,1); %generowane zapotrzebowanie kazdego miasta
%zapotrz_laczne=sum(zapotrzebowanie); %³¹czne zapotrzebowanie
%ile_samochodow=round(2*zapotrz_laczne/(min_zaladunek+max_zaladunek) -0.2); %ile samochodow wyjedzie w trase
%czas=[0 0 0];       %przechowuje czasy
save('zmienne');
