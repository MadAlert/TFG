# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json
import csv, operator

page = requests.get("https://www.madridiario.es/indice-distritos/")

soup = BeautifulSoup(page.content, 'html.parser')

entradas = soup.find_all('li')
listaTitulo = []

csvSalida = open("newWeb.csv", "w", encoding = "utf-8")

cont = 0

for i, entrada in enumerate(entradas):
    link = entrada.find('a').get('href') # con esto obtenemos el link a todos los distritos
   
    page2 = requests.get(link)
    soup2 = BeautifulSoup(page2.content, 'html.parser')
    distritos = soup2.find_all(class_="fueraNoticia")

    for j, dist in enumerate(distritos): # entra al distrito j y obtiene el titulo, la entradilla de la noticia y la url de la misma
        titulo = dist.find(class_='titulo').get_text()
        listaTitulo.append(titulo)
        #print(listaTitulo[cont])
        csvSalida.write(str(listaTitulo[cont]))
        csvSalida.write(";\n")
        cont = cont + 1

print("csv created")
csvSalida.close()
