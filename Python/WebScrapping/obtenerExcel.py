#-*- coding=utf-8 -*-

from bs4 import BeautifulSoup
import requests
import wget

init = 'http://datos.madrid.es'
url = 'https://datos.madrid.es/portal/site/egob/menuitem.c05c1f754a33a9fbe4b2e4b284f1a5a0/?vgnextoid=bffff1d2a9fdb410VgnVCM2000000c205a0aRCRD&vgnextchannel=374512b9ace9f310VgnVCM100000171f5a0aRCRD&vgnextfmt=default'
page = requests.get(url)
print(page.text)

soup = BeautifulSoup(page.text, 'html.parser')

entradas = soup.find_all(class_='asociada-link ico-xlsx')
print(entradas)

link = entradas[0].get('href') # esto devuelve el link del ultimo mes

#for link in entradas: # esto devuelve los links de todos los meses que hay
 #  print(link.get('href'))

excel = init + link
filename = wget.download(excel, out='./excel/')
print(filename)
