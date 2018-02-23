#-*- coding=utf-8 -*-

import time
import datetime

x = datetime.datetime.now()
month = x.month

class ParseoMesClass():
	def parseoMes(self, mes):
		m = ""
		if mes == 1:			
			m = "Enero"
		if mes == 2:
			m = "Febrero"
		if mes == 3:
			m = "Marzo"
		if mes == 4:
			m = "Abril"
		if mes == 5:
			m = "Mayo"
		if mes == 6:
			m = "Junio"
		if mes == 7:
			m = "Julio"
		if mes == 8:
			m = "Agosto"
		if mes == 9:
			m = "Septiembre"
		if mes == 10:
			m = "Octubre"
		if mes == 11:
			m = "Noviembre"
		if mes == 12:
			m = "Diciembre"
		if mes == -1 and month == 1:
			m = "Diciembre"
		if mes == -2 and month == 1:
			m = "Noviembre"
		if mes == 0 and month == 2:
			m = "Diciembre"
		return m

