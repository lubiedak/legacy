function ilekilometrow = odleglosc(start,miasta,odleglosci)
dyst=odleglosci(start,miasta(1));
ile=length(miasta)-1;
for i=1:ile
    dyst=dyst+odleglosci(miasta(i),miasta(i+1));
end
ilekilometrow=dyst+odleglosci(miasta(i+1),start);
    
end