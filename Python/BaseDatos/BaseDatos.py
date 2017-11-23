from pymongo import *
from datetime import *
from time import *


class baseDatosClass():
    def conexion(self):
        #conexion= Connection()
        conexion = MongoClient('localhost', 27017)
       # db = conexion['noticias'] #conexion a la base de datos que utilizamos
        db = conexion.noticias
        coleccion = db.alertas #coleccion
        return coleccion
    def desconexion(self):
        mongoClient.close()
    def insertarAlerta(self,coleccion, alerta, fecha, url, distrito, categoria, fuente):
        diccionario = {"alerta": alerta, "fecha" : fecha, "url" : url, "distrito": distrito, "categoria": categoria, "fuente": fuente}
        coleccion.insert(diccionario)
    #def eliminarAlertas(fecha)
                


bd = baseDatosClass()
c=bd.conexion()
bd.insertarAlerta(c, "Lluvia fuerte", "12/05/2017","www.google.com", "chamberi","desastre","ppi")







