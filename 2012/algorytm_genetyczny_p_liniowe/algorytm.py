import os
import subprocess
import shutil
import array
import sys
import math
import random
import operator


def ReadCSV(fileName):
    table = []
    csvFile = open(fileName)
    for line in csvFile:
        line = line[0:-1]
        temp=line.split(";")
        temp2=[]
        for cell in temp:
            try:
                cell=int(cell)
            except:
                cell=cell
            temp2.append(cell)
        print (temp2)
        table.append(temp2)
    csvFile.close()

    return table

def Generuj_Populacje(licznosc, wypisz = False):
    dict_pop={}
    populacja = []
    for i in range(licznosc):
        osobnik = []
        for j in range(len(dane)):
            osobnik.append(random.randint(0, len(dane[j])-2))
        
        dict_pop[Ocen_Osobnika(osobnik)]=osobnik
        if wypisz:
            print (osobnik)
    x=sorted(dict_pop)
    for i in x:
        populacja.append(dict_pop[i])
    return populacja

def Odchylenie(osobnik):
    srednia = math.fsum(osobnik)/len(osobnik)
    suma=0
    for i in osobnik:
        suma+=(srednia-i)*(srednia-i)
    
    return math.sqrt(suma/len(osobnik))
    
def Suma_Cena(osobnik):
    suma=0
    for i in range(len(osobnik)):
        suma+=dane[i][osobnik[i]+1]
    return suma
    
def Ocen_Osobnika(osobnik, opcja=1):
    odchylenie=Odchylenie(osobnik)
    cena = Suma_Cena(osobnik)
    roznica=math.fabs(CEL-Suma_Cena(osobnik))
    
    if opcja==1:
        return ocena
    if opcja==2:
        return ocena, cena

def Krzyzowanie (os1, os2, wypisz = False):
    miejsce_podzialu = random.randint(1,len(os1)-1)
    new_os1=os1[0:miejsce_podzialu]
    new_os1.extend(os2[miejsce_podzialu:])
    new_os2=os2[0:miejsce_podzialu]
    new_os2.extend(os1[miejsce_podzialu:])
    if wypisz:
        print (os1)
        print (os2)
        print (new_os1[:miejsce_podzialu], new_os1[miejsce_podzialu:])
        print (new_os2[:miejsce_podzialu], new_os2[miejsce_podzialu:])
    return new_os1,new_os2

def Krzyzuj_Wszystko(populacja, ile = 5):
    pop=[]
    for i in range(ile):
        for j in range (ile-i-1):
            pop.extend(Krzyzowanie(populacja[i],populacja[j+1]))
    dict_pop=[]
    for i in pop:
        dict_pop.append((Ocen_Osobnika(i),i))
    dict_pop_sort=sorted(dict_pop, key=lambda x: x[0])
    new_pop=[]
    for i,j in dict_pop_sort:
        new_pop.append(j)
    return new_pop    
    
def Mutacja (osobnik, wypisz = False):
    miejsce_mutacji = random.randint(0,len(osobnik)-1)
    old = osobnik[miejsce_mutacji]
    new = random.randint(0,len(dane[miejsce_mutacji])-2)
    osobnik[miejsce_mutacji] = new
    while (new==old):
        new = random.randint(0,len(dane[miejsce_mutacji])-2)
        osobnik[miejsce_mutacji] = new
    if wypisz:
        print(osobnik[:miejsce_mutacji],osobnik[miejsce_mutacji],osobnik[miejsce_mutacji+1:])
    return osobnik

def Wypisz_i_Ocen (populacja):
    for i in populacja:
        print (i, Ocen_Osobnika(i,2))

        
global CEL
CEL=1000
ile_x = 6
rozmiar_populacji= ile_x * (ile_x-1)
czestosc_mutacji = 10

print ("DANE.CSV:")
global dane
dane = ReadCSV("dane.csv")

print ("\n\nWYGENEROWANA POPULACJA:")
populacja=Generuj_Populacje(rozmiar_populacji)
Wypisz_i_Ocen(populacja)
newpop=[]

print ("\n\n Ile generacji mam przetworzyc?")
ile_gen=int(input())

for j in range(ile_gen):
    populacja=Krzyzuj_Wszystko(populacja, ile_x)
    for i in range(czestosc_mutacji):
        ktory_mutujemy=random.randint(0,rozmiar_populacji-1)
        populacja[ktory_mutujemy]=Mutacja(populacja[ktory_mutujemy])
    print ("\n\nGENERACJA  ",str(j+1),":")
    Wypisz_i_Ocen(populacja)