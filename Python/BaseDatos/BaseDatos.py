#!/usr/bin/env python
# -*- coding: utf-8 -*-
from pymongo import *
from datetime import *
from time import *

class baseDatosClass():
    apikey = "AkF2jtWisCYb4wZoibvcPO60HUUQRKC1"
   
    def conexion(self):
       MONGODB_URI = "mongodb://MadAlert:tfg20172018@ds235388.mlab.com:35388/noticias"
       conexion = MongoClient(MONGODB_URI, connectTimeoutMS=30000)
       return conexion.get_default_database()
       
    def conexionAlertas(self, conexion):
        coleccion = conexion.alertas 
        return coleccion
    
    def conexionEstadisticas(self, conexion):
        coleccion = conexion.estadisticas 
        return coleccion

    def conexionEstSeguridad(self, conexion):
        coleccion = conexion.estSeguridad 
        return coleccion

    def conexionEstDetenidos(self, conexion):
        coleccion = conexion.estDetenidos 
        return coleccion    
    
    def conexionEstAccidentes(self, conexion):
        coleccion = conexion.estAccidentes 
        return coleccion    

    def desconexion(self):
        mongoClient.close()
        
    def insertarAlerta(self, coleccion, alerta, fecha, url, distrito, categoria, fuente):
        diccionario = {"alerta": alerta, "fecha" : fecha, "url" : url, "distrito": distrito, "categoria": categoria, "fuente": fuente}
        coleccion.insert_one(diccionario)

    def insertarEstadisticas(self, coleccion, distrito, categoria, mes):
        diccionario = {"distrito": distrito, "categoria": categoria, "mes": mes}
        coleccion.insert_one(diccionario)
  
    def insertarEstSeguridad(self, coleccion, distrito, personas, patrimonio, armas, ten_drogas, con_drogas, mes):
        diccionario = {"distrito": distrito, "personas": personas, "patrimonio": patrimonio,
                        "armas": armas, "ten_drogas": ten_drogas, "con_drogas": con_drogas, "mes" : mes}
        coleccion.insert_one(diccionario)

    def insertarEstDetenidos(self, coleccion, distrito, detenidos, mes):
        diccionario = {"distrito": distrito, "detenidos": detenidos, "mes": mes}
        coleccion.insert_one(diccionario)

    def insertarEstAccidentes(self, coleccion, distrito, conHeridos, sinHeridos, mes):
        diccionario = {"distrito": distrito, "conHeridos": conHeridos, "sinHeridos": sinHeridos, "mes": mes}
        coleccion.insert_one(diccionario)

    def eliminarAlerta(self, coleccion, fecha):
        coleccion.remove({"fecha":{'$lte':fecha}})
        
    def eliminarEstadisticas(self, coleccion, mes, mesActual):
        coleccion.remove({"mes":{'$lte':mes}})
        coleccion.remove({"mes":{'$gt':mesActual}})
        
    def eliminarEstSeguridad(self, coleccion,  mes, mesActual):
        coleccion.remove({"mes":{'$lte':mes}})
        coleccion.remove({"mes":{'$gt':mesActual}})
        
    def eliminarEstDetenidos(self, coleccion,  mes, mesActual):
        coleccion.remove({"mes":{'$lte':mes}})
        coleccion.remove({"mes":{'$gt':mesActual}})

    def eliminarEstAccidentes(self, coleccion,  mes, mesActual):
        coleccion.remove({"mes":{'$lte':mes}})
        coleccion.remove({"mes":{'$gt':mesActual}})



