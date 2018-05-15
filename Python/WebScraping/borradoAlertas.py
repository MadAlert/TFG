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
bdAlertas = bd.conexionAlertas(con)

#Calculo la fecha actual
fechaActual = datetime.now()

#Resto 3 dias
dias = relativedelta(days=3)
fecha_menos_dias = fechaActual - dias

#Elimino las alertas que tengan fecha inferior a la anteriormente calculada
bd.eliminarAlerta(bdAlertas, fecha_menos_dias ) 
