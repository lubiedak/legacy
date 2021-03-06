clear;
clc;

%%%%%%%%%%%%%%%%%%%%%%%%KRYTERIA%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
N=11;             % N liczba odwiedzanych miast

min_zaladunek=19; % minimalny za�adunek samochodu
max_zaladunek=28; % maksymalny za�adunek samochodu

max_miast=4;          % maksymalna liczba miast, ktore mo�e obslugiwac komiwojazer

xy=20*rand(N+1,2);     % losowanie wspolrzednych miast 

zapotrzebowanie=5+9.*rand(N,1); %generowane zapotrzebowanie kazdego miasta
zapotrz_laczne=sum(zapotrzebowanie); %��czne zapotrzebowanie
ile_samochodow=round(2*zapotrz_laczne/(min_zaladunek+max_zaladunek) -0.2); %ile samochodow wyjedzie w trase
czas=[0 0 0];       %przechowuje czasy, konieczne do sprawdzenia jak d�ugo si� wykonuje algorytm

%%%%%%%%%%%%%%%%%%%%%%%%%%POCZATEK PROGRAMU%%%%%%%%%%%%%%%%%%%%%%%%
czas(1)=rem(now,1); %czas wykonywania programu

dystanse=generuj_macierz_dystansow(xy);
save('zmienne'); %zapisanie zmiennych, aby inne podfunkcje mog�y z nich korzytac
opcje_1_tir=generuj_opcje_1_tir(dystanse); %funkcja generuje opcje dostaw spelniajacych kryteria

opcje_skrot=opcje_1_tir(:,1:6);

czas(2)=rem(now,1); %czas(2)-czas(1) - czas generowania wszystkich cykli

opcje01=opcje_1_tir(:,7:N+6); % OPERACJE KONIECZNE DO ROZPOCZ�CIA ��CZENIA CYKLI
b=transpose(1:size(opcje_1_tir,1));
c=opcje_1_tir(:,6);
propozycje=[c b];


for i=2:ile_samochodow
   [opcje01 propozycje]=laczenie(i,N,c,propozycje,opcje01,opcje_1_tir(:,7:N+6));    
end

%WY�WIETLENIE KILKU PROPOZYCJI:
wyswietl=zeros(2,size(propozycje,2)+1);
for i=1:size(opcje01,1)
wyswietl(i,:)=[propozycje(i,:) sum(opcje01(i,:))];
end
posortowane=sortrows(wyswietl,1);
pelne=find(posortowane(1:end,end)==N);
best_complete=posortowane(pelne,:);

for i=1:4
subplot(2,2,i)
narysuj_mape_miast(xy,zapotrzebowanie);
rysuj_najlepsze(N+1,xy,opcje_skrot(best_complete(i,2:end-1),:))
title(num2str(best_complete(i,1),'%5.2f'))
end

czas(3)=rem(now,1); %czas wykonywania programu

24*3600*(czas(3)-czas(1)) %czas w sekundach