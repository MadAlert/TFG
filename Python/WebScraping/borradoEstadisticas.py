#!/usr/bin/env python
# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import requests
import json
import sys
sys.path.append('../')

from BaseDatos import BaseDatos
import ParseoFecha
from datetime import datetime, timedelta
from dateutil.relativedelta import relativedelta

bd = BaseDatos.baseDatosClass()
con = bd.conexion()
bdEstadisticas = bd.conexionEstadisticas(con)


#Calculo la fecha actual
fechaActual = datetime.now()

#Resto 3 meses
mesActual = fechaActual.month
mes = mesActual - 3

print("resta:")
print(mes)
print("actual:")
print(mesActual)
#meses = relativedelta(months=3)
#fecha_menos_meses = fechaActual - meses

#Elimino las alertas que tengan fecha inferior a la anteriormente calculada
bd.eliminarEstadisticas(bdEstadisticas, mes , mesActual) 

