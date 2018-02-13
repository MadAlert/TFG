#!/usr/bin/env python
# -*- coding: utf-8 -*-
from pymongo import *
from datetime import *
from time import *


class baseDatosClass():
    def conexion(self):
       #conexion= Connection()
       conexion = MongoClient('localhost', 27017)
       return conexion
       # db = conexion['noticias'] #conexion a la base de datos que utilizamos
       
    def conexionAlertas(self, conexion):
        db = conexion.noticias
        coleccion = db.alertas #coleccion
        return coleccion
    
    def conexionEstadisticas(self, conexion):
        db = conexion.noticias
        coleccion = db.estadisticas #coleccion
        return coleccion

    def conexionEstSeguridad(self, conexion):
        db = conexion.noticias
        coleccion = db.estSeguridad #coleccion
        return coleccion

    def conexionEstDetenidos(self, conexion):
        db = conexion.noticias
        coleccion = db.estDetenidos #coleccion
        return coleccion    
    
    def desconexion(self):
        mongoClient.close()
        
    def insertarAlerta(self, coleccion, alerta, fecha, url, distrito, categoria, fuente):
        diccionario = {"alerta": alerta, "fecha" : fecha, "url" : url, "distrito": distrito, "categoria": categoria, "fuente": fuente}
        coleccion.insert(diccionario)

    def insertarEstadisticas(self, coleccion, distrito, categoria, mes):
        diccionario = {"distrito": distrito, "categoria": categoria, "mes": mes}
        coleccion.insert(diccionario)
    #def eliminarAlertas(fecha)

    def insertarEstSeguridad(self, coleccion, distrito, personas, patrimonio, armas, ten_drogas, con_drogas):
        diccionario = {"distrito": distrito, "personas": personas, "patrimonio": patrimonio,
                        "armas": armas, "ten_drogas": ten_drogas, "con_drogas": con_drogas}
        coleccion.insert(diccionario)

    def insertarEstDetenidos(self, coleccion, distrito, detenidos):
        diccionario = {"distrito": distrito, "detenidos": detenidos}
        coleccion.insert(diccionario)





