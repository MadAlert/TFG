# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json
import sys
sys.path.append('../')
from Clasificador import Clasificador
from BaseDatos import BaseDatos
import ParseoFecha
from datetime import datetime, timedelta

page = requests.get("https://www.madridiario.es/indice-distritos/")
#print(page.status_code)

soup = BeautifulSoup(page.content, 'html.parser')
#print(soup.prettify()) #muestra el contenido HTML mejor presentado

entradas = soup.find_all('li')

#Instancia a la clase Clasificador
clasificador = Clasificador.ClasificadorClass()

bd = BaseDatos.baseDatosClass()
c = bd.conexion()
f = ParseoFecha.ParseoFechaClass()

for i, entrada in enumerate(entradas): 
    link = entrada.find('a').get('href') # con esto obtenemos el link a todos los distritos
    distrito = entrada.find('a').get_text()
    
    page2 = requests.get(link)
    soup2 = BeautifulSoup(page2.content, 'html.parser')
    distritos = soup2.find_all(class_="fueraNoticia")

    salir = False

    #entra al distrito j y obtiene el titulo, la entradilla de la noticia y la url de la misma
    for j, dist in enumerate(distritos):
        if(salir==False): # esto es necesario porque si la fecha no es la que queremos, se sale del distrito
            titulo = dist.find(class_='titulo').get_text()
            entradilla = dist.find(class_='entradilla').get_text()
    	    #print("%d" % j)
    	    #print(titulo)
            print(titulo)
            print(entradilla)
            url = dist.find('a').get('href')
            print(url)
            #Obtener categoria
            #lista = []
            #lista.append(titulo)
            categoria = clasificador.clasificadorNoticias(titulo)
            page3 = requests.get(dist.find('a').get('href'))
            soup3 = BeautifulSoup(page3.content, 'html.parser')
            inside = soup3.find_all(class_='sin_borde')
            print(categoria)
            if(categoria != "Nada"):
                for k, insi in enumerate(inside): # entra en la noticia k para obtener la fecha
                    fechaPre = insi.find(class_='ulthora fecha_publicacion').get_text()
                    fecha = f.parseo(fechaPre)
                    print(fecha)
                # Instancia a la base de datos
                # Cuando inserta a Mongo la fecha al final muestra una 'Z'. Esto es Zulu Time, lo que nosotros conocemos como UTC Time
                # aqui es donde hay que crear lo de la fecha
                dif = datetime.now() - timedelta(minutes=5)  
                fd = datetime.strptime(fecha, "%d-%m-%Y %H:%M:%S")
                if(fd > dif): # si la fecha de la noticia es superior a la hora_actual - 5 min se tiene que guardar
                    bd.insertarAlerta(c,titulo,fecha,url,distrito,categoria,"madridDiario")
                else:
                    salir = True
        
def var():
    distritos = [ "arganzuela", "barajas", "carabanchel", "centro", "chamartin", "chamberi", "ciudad lineal", "fuencarral-el pardo", "hortaleza", "latina", "moncloa-aravaca", "moratalaz", "puente de vallecas", "retiro", "salamanca", "san blas", "tetuan", "usera", "vicalvaro", "villa de vallecas", "villaverde"]
    return (distritos)
