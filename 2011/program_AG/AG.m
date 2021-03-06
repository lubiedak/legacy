clear
clc

N=20;   %liczba osobnikow w populacji

M=15;   %liczba chromosomow ka�dego osobnika

T=1;    %czas, Matlab zaczyna numeracje macierzy od 1

Tmax=20; %maksymalna liczba wywola� algorytmu

P=zeros(Tmax,N,M); %macierz przechowujacej poszczegolne pokolenia populacji

Ocena=zeros(Tmax,N);
%funkcja oceny y=20-5.*sin(50.*x).*cos(32.*x)-10.*(x-1).^2;
% cz�� odpowiedzialna za wylosowanie pierwszej populacji
P(T,:,:)=rand(N,M);
for i=1:N
    for j=1:M
        if P(T,i,j)>0.5
            P(T,i,j)=1;
        else
            P(T,i,j)=0;
        end
    end
end

Ocena(T,:)=ocen_osobniki(P(T,:,:));

historia_rozrodu=zeros(Tmax-1,10);

for T=2:Tmax
    [do_rozrodu ktore]=wybierz(P(T-1,:,:),Ocena(T-1,:)); %zawsze bedzie to 5 osobnikow
    historia_rozrodu(T-1,:)=ktore; %za ka�dym razem rejestrujemy, ktore osobniki zosta�y wybrane do rozrodu
    nowa_populacja=krzyzuj(do_rozrodu);
    nowa_populacja=mutuj(nowa_populacja);
    
    P(T,:,:)=nowa_populacja;
    Ocena(T,:)=ocen_osobniki(P(T,:,:));
end

srednia_ocena=zeros(Tmax,1);
najlepszy_osobnik=zeros(Tmax,1);

for i=1:Tmax
    srednia_ocena(i)=mean(Ocena(i,:));
    najlepszy_osobnik(i)=max(Ocena(i,:));
end
    srednia_ocena;
najlepszy_osobnik;
subplot(2,1,1)
plot(srednia_ocena);
title('Srednia przystosowania z calej populacji')
subplot(2,1,2)
plot(najlepszy_osobnik);
title('Najlepszy osobnik w danej generacji')