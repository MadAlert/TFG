#!/usr/bin/env python
# -*- coding: utf-8 -*-
import openpyxl
import os
import sys
sys.path.append('../')
from BaseDatos import BaseDatos
import parseoDistrito

# conexion a la bd y a la coleccion que queremos
bd = BaseDatos.baseDatosClass()
con = bd.conexion()
bdEstSeguridad = bd.conexionEstSeguridad(con)
bdEstDetenidos = bd.conexionEstDetenidos(con)


# Obtiene el archivo del que va a tomar los datos
ruta_app = './excel/'  # obtiene ruta del script
contenido = os.listdir(ruta_app)  # obtiene lista con archivos/dir 
route = ruta_app + contenido[0]

# doc = excel con los datos que queremos
doc = openpyxl.load_workbook(route)
print(doc)

# hojas del excel a examinar SEGURIDAD
doc.sheetnames
hoja = doc['SEGURIDAD']
# hojas del excel a examinar DETENIDOS
hoja2 = doc['DETENIDOS X DISTRITOS']

seleccion = hoja['A4':'F24']
for filas in seleccion:
    for i in range(0,6):
        if i == 0:
            distrito = filas[i].value.encode("utf-8")
            #print("Distrito: " + distrito)
        if i == 1:
            personas = filas[i].value
            #print("Personas: " + str(personas))
        if i == 2:            
            patrimonio = filas[i].value
            #print(patrimonio)
        if i == 3:
            armas = filas[i].value
            #print(armas)
        if i == 4:
            ten_drogas = filas[i].value
            #print(ten_drogas)
        if i == 5:
            con_drogas = filas[i].value
            #print(con_drogas)
    d = parseoDistrito.ParseoDistritoClass()
    bd.insertarEstSeguridad(bdEstSeguridad, d.parseoDistrito(distrito), personas, patrimonio, armas, ten_drogas, con_drogas)

print("Datos insertados en la bd EstSeguridad")

seleccion2 = hoja2['A4':'B24']
for filas in seleccion2:
    for i in range(0,2):
        if i == 0:
            distrito = filas[i].value.encode("utf-8")
            #print("Distrito: " + distrito)
        if i == 1:
            detenidos = filas[i].value
            #print("Detenidos: " + str(detenidos))
    d = parseoDistrito.ParseoDistritoClass()
    bd.insertarEstDetenidos(bdEstDetenidos, d.parseoDistrito(distrito), detenidos)

print("Datos insertados en la bd EstDetenidos")


    
