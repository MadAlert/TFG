# -*- coding: utf-8 -*-

class ParseoCategoriaClass:
    def parseoCategoria(self, codigo):
    	c = ""
    	if codigo == "1":
    		c = "Desastres y accidentes"
    	if codigo == "2":
    		c =  "Contaminación"  		
    	if codigo == "3":
    		c = "Eventos"
    	if codigo == "4":
    		c = "Criminalidad"
    	if codigo == "5":
    		c =  "Nada"   		
    	if codigo == "6":
    		c = "Tráfico"
    	if codigo == "7":
    		c = "Transporte público"
    	if codigo == "8":
    		c = "Terrorismo"
    	return c