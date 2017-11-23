# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json
import sys
sys.path.append('../')
from Clasificador import Clasificador
from BaseDatos import BaseDatos

page = requests.get("https://www.madridiario.es/indice-distritos/")
#print(page.status_code)

soup = BeautifulSoup(page.content, 'html.parser')
#print(soup.prettify()) #muestra el contenido HTML mejor presentado

entradas = soup.find_all('li')

#Instancia a la clase Clasificador
c = Clasificador.ClasificadorClass()

bd = BaseDatos.baseDatosClass()
c = bd.conexion()

for i, entrada in enumerate(entradas): 
    link = entrada.find('a').get('href') # con esto obtenemos el link a todos los distritos
    print(link)
    
    page2 = requests.get(link)
    soup2 = BeautifulSoup(page2.content, 'html.parser')
    distritos = soup2.find_all(class_="fueraNoticia")

    #entra al distrito j y obtiene el titulo, la entradilla de la noticia y la url de la misma
    for j, dist in enumerate(distritos):
        titulo = dist.find(class_='titulo').get_text()
        entradilla = dist.find(class_='entradilla').get_text()
	#print("%d" % j)
	#print(titulo)
        print(titulo)
        print(entradilla)
        url = dist.find('a').get('href')
        print(url)
        #Obtener categoria
        lista = []
        lista.append(titulo)
        categoria = c.clasificadorNoticias(lista)
        page3 = requests.get(dist.find('a').get('href'))
        soup3 = BeautifulSoup(page3.content, 'html.parser')
        inside = soup3.find_all(class_='sin_borde')
        print(categoria)
        for k, insi in enumerate(inside): # entra en la noticia k para obtener la fecha
            fecha = insi.find(class_='ulthora fecha_publicacion').get_text()
            print(fecha)
        #Instancia a la base de datos 
        bd.insertarAlerta(c,titulo,fecha,url,distrito,categoria,"madridDiario")
    
def var():
    distritos = [ "arganzuela", "barajas", "carabanchel", "centro", "chamartin", "chamberi", "ciudad lineal", "fuencarral-el pardo", "hortaleza", "latina", "moncloa-aravaca", "moratalaz", "puente de vallecas", "retiro", "salamanca", "san blas", "tetuan", "usera", "vicalvaro", "villa de vallecas", "villaverde"]
    return (distritos)
