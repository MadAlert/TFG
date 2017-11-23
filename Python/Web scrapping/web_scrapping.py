# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json

page = requests.get("https://www.madridiario.es/indice-distritos/")
#print(page.status_code)

soup = BeautifulSoup(page.content, 'html.parser')
#print(soup.prettify()) #muestra el contenido HTML mejor presentado

entradas = soup.find_all('li')

for i, entrada in enumerate(entradas): 
    link = entrada.find('a').get('href') # con esto obtenemos el link a todos los distritos
    print(link)
    
    page2 = requests.get(link)
    soup2 = BeautifulSoup(page2.content, 'html.parser')
    distritos = soup2.find_all(class_="fueraNoticia")

    for j, dist in enumerate(distritos): # entra al distrito j y obtiene el titulo, la entradilla de la noticia y la url de la misma
		print("%d" % j)
        titulo = dist.find(class_='titulo').get_text()
        print(titulo)
        entradilla = dist.find(class_='entradilla').get_text()
        print(entradilla)
        url = dist.find('a').get('href')
        print(url)
    
        page3 = requests.get(dist.find('a').get('href'))
        soup3 = BeautifulSoup(page3.content, 'html.parser')
        inside = soup3.find_all(class_='sin_borde')

        for k, insi in enumerate(inside): # entra en la noticia k para obtener la fecha
            fecha = insi.find(class_='ulthora fecha_publicacion').get_text()
            print(fecha)
    
    
def var():
    distritos = [ "arganzuela", "barajas", "carabanchel", "centro", "chamartin", "chamberi", "ciudad lineal", "fuencarral-el pardo", "hortaleza", "latina", "moncloa-aravaca", "moratalaz", "puente de vallecas", "retiro", "salamanca", "san blas", "tetuan", "usera", "vicalvaro", "villa de vallecas", "villaverde"]
    return (distritos)
