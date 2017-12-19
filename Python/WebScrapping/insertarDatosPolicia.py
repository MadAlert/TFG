import openpyxl
import os

ruta_app = './excel/'  # obtiene ruta del script
contenido = os.listdir(ruta_app)  # obtiene lista con archivos/dir 
route = ruta_app + contenido[0]

doc = openpyxl.load_workbook(route)
print(doc)

doc.get_sheet_names()
hoja = doc.get_sheet_by_name('SEGURIDAD')
hoja.title

for fila in hoja.rows:
    for columna in fila:
        print(columna.value)
    print("")
