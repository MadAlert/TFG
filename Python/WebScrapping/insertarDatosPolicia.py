import openpyxl
import os
import sys
sys.path.append('../')
from BaseDatos import BaseDatos

# conexion a la bd y a la coleccion que queremos
bd = BaseDatos.baseDatosClass()
con = bd.conexion()
bdEstSeguridad = bd.conexionEstSeguridad(con)


# Obtiene el archivo del que va a tomar los datos
ruta_app = './excel/'  # obtiene ruta del script
contenido = os.listdir(ruta_app)  # obtiene lista con archivos/dir 
route = ruta_app + contenido[0]

# doc = excel con los datos que queremos
doc = openpyxl.load_workbook(route)
print(doc)

# hojas del excel a examinar
doc.get_sheet_names()
hoja = doc.get_sheet_by_name('SEGURIDAD')

seleccion = hoja['A4':'F25']
for filas in seleccion:
    for i in range(0,6):
        if i == 0:
            distrito = filas[i].value
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
    bd.insertarEstSeguridad(bdEstSeguridad, distrito, personas, patrimonio, armas, ten_drogas, con_drogas)
