function [] = linia(xy,punkt1,punkt2,kolor)
line([xy(punkt1,1) xy(punkt2,1)],[xy(punkt1,2) xy(punkt2,2)],'Color',kolor)
end