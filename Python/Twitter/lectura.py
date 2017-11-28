import json
import io
import time
import sys
sys.path.append('../')
from Clasificador import Clasificador
import os
from BaseDatos import BaseDatos

fichero = "tweets"

def leerDatos():
    #Abrimos el fichero donde estan los tweets guardados
        bd = BaseDatos.baseDatosClass()
        con = bd.conexion()
        c = Clasificador.ClasificadorClass()
        fechaActual = time.strftime("%d%m%y")
        file=fichero+fechaActual+".json"
        for line in open(file, "r", encoding="utf8"):
            carga = json.loads(line)
            tweet = carga["text"]
            lista = []
            lista.append(tweet)
            categoria = c.clasificarTweets(lista)
            if(categoria != "Nada"):
                fechaCreacion = carga["created_at"]
                nombreUsuario = "@"+carga["user"]["screen_name"]
                zona = c.clasificadorZona(lista)
                print ("La categoria es : " + categoria)
                print ("El usuario es " + nombreUsuario)
                print ("El tweet es " + tweet)
                print ("La fecha es " + fechaCreacion)
                print ("La zona es " + zona)
                bd.insertarAlerta(con,tweet,fechaCreacion,None,zona,categoria,nombreUsuario)
                
           

leerDatos()
