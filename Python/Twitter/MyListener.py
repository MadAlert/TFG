import tweepy
import io
import json
import var
import time
import sys
sys.path.append('../')
from Clasificador import Clasificador
import os
from BaseDatos import BaseDatos

from tweepy import Stream
from tweepy.streaming import StreamListener

fichero = "tweets"
usuarios = var.var()

class listener(StreamListener):
    def on_data(self, data):
        try:
            data.rstrip('\n')
            carga = json.loads(data)
            mencionVacia = False
            mencionInteresa = False
            #existe mencion
            if "entities" in carga:
                #Obtenemos la mencion
                mencion =carga["entities"]["user_mentions"]
                #La lista de menciones es vacia, es porque no es una mencion
                if (not mencion):
                    mencionVacia= True
                #Es una mencion de un usuario de la lista a otro
                elif ((mencion[0]["id_str"]in usuarios) and (carga["user"]["id_str"] in usuarios)):
                    mencionInteresa= True
            if "created_at" in carga and not("RT" in carga["text"]) and (mencionVacia or mencionInteresa):
                self.insertarDatos(carga);
                print("Me sirve")
            else:
                print("Este tweet no me importa")
            return True
        except BaseException as e:
            print ("Error" + e)
        return True

    def on_error(self, status):
        print(status)
        return True

    def insertarDatos(self, carga):
        bd = BaseDatos.baseDatosClass()
        con = bd.conexion()
        bdAlertas = bd.conexionAlertas(con)
        bdEstadisticas = bd.conexionEstadisticas(con)
        c = Clasificador.ClasificadorClass()
        tweet = carga["text"]
        lista = []
        lista.append(tweet)
        categoria = c.clasificarTweets(tweet)
        print (categoria);
        if(categoria != "Nada"):
            fecha= time.strftime('%d-%m-%Y %H:%M:%S', time.strptime(carga['created_at'],'%a %b %d %H:%M:%S +0000 %Y'))
            mes = time.strftime('%m', time.strptime(carga['created_at'],'%a %b %d %H:%M:%S +0000 %Y'))
            print (fecha)
            nombreUsuario = "@"+carga["user"]["screen_name"]
            zona = c.clasificadorZona(lista)
            print(zona)
            bd.insertarEstadisticas(bdEstadisticas,zona,categoria,mes)
            bd.insertarAlerta(bdAlertas,tweet,fecha,None,zona,categoria,nombreUsuario)
        
