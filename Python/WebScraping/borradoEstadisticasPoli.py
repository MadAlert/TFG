#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json
import sys
sys.path.append('../')
from BaseDatos import BaseDatos
from datetime import datetime, timedelta

# Conexion a la bd (estadisticas policias)
bd = BaseDatos.baseDatosClass()
con = bd.conexion()
bdEstSeguridad = bd.conexionEstSeguridad(con)
bdEstDetenidos = bd.conexionEstDetenidos(con)
bdEstAccidentes = bd.conexionEstAccidentes(con)


#Calculo la fecha actual
fechaActual = datetime.now()

#Resto 3 meses
mesActual = fechaActual.month
mes = mesActual - 3

#Elimino las alertas que tengan fecha inferior a la anteriormente calculada 
bd.eliminarEstSeguridad(bdEstSeguridad, mes , mesActual) 
bd.eliminarEstDetenidos(bdEstDetenidos, mes , mesActual)
bd.eliminarEstAccidentes(bdEstAccidentes, mes , mesActual)
