# -*- coding: UTF-8 -*-
'''
Created on 26/03/2015

@author: cvargasc
'''
##########################
## IMPORTS
##########################
import os
import pprint

##########################
## CONSTANTES
##########################
DIRECTORIO_ENTRADA = 'data/evaluaciones'
ARCHIVO_SALIDA = 'data/resultados.csv'

##########################
## ATRIBUTOS
##########################
grupos = {
	'3D Home': ['la.avila30','ca.barrientos1030','ja.angel904'],
	'Aster': ['pa.otoya757','jp.lalinde1079','da.gomez11','sd.hernandez204','ad.sanchez10'],
	'Crowd Control': ['m.villamizar564','lc.mojica639','ol.pino521','ds.chicaiza10','sd.salas10'],
	'Enigami': ['ce.forero2551','f.mate10','s.beltran10','s.siza10'],
	'OndeHay': ['jj.rodriguez644','we.gamba125','ca.ortiz1633','da.paternina1691'],
	'GIS': ['s.abisambra125','jc.ruiz160','mp.mancipe10'],
	'Should I?': ['f.otalora10','me.hernandez10','nf.chaves10','s.florez10'],
	'Temporizate': ['jh.gomez1793','ba.hurtado564','ea.cabello10','ja.de83'],
	'TicketService': ['f.iregui310','n.sanchez1668','d.rodriguez13','hf.vargas10'],
	'TransMotion': ['de.dluyz1309','cd.bedoya212','jj.diaz1067','js.salamanca1967'],
	'moveCam': ['je.bautista10','pa.sarmiento10']
	}

##########################
## SCRIPT
##########################
pp = pprint.PrettyPrinter()

alumnos = {} # Diccionario que contrandrá la evaluación enviada por cada alumno
penalizaciones = {} # Diccionario que contendrá las penalizaciones aplicadas
advertencias = [] # Lista que contendrá las advertencias generadas durante el proceso

for grupo, integrantes in grupos.items():
	print '\n'+grupo, ':: '+str(len(integrantes))+' integrantes'; 
	for integrante in integrantes:
		# Se inicializa con la asignación que se realiza por defecto si no se manda la evaluación
		alumnos[integrante] = {}
		for companiero in integrantes:
			if integrante != companiero:
				alumnos[integrante][companiero] = 3 # A cada compañero se asigna 3 por defecto
			else:
				alumnos[integrante][companiero] = -1 # Penalización que se aplica en caso de no enviar la evaluación
		print '  ', integrante, alumnos[integrante]
print '\n ----------------------------------------------------------------- \n'
# Realizando limpieza:
if os.path.exists(ARCHIVO_SALIDA): 
	os.remove(ARCHIVO_SALIDA) # Eliminando el archivo de salida, si existe

evaluaciones = os.listdir(DIRECTORIO_ENTRADA)
os.chdir(DIRECTORIO_ENTRADA)
for evaluacion in evaluaciones:
	if len(evaluacion.split('_')) == 4 or not evaluacion.endswith('txt'):
		os.remove(evaluacion) # Eliminando el archivo adicional que genera SICUA

evaluaciones = os.listdir('.')
for evaluacion in evaluaciones:
	alumno = evaluacion.split('_')[1] # Obteniendo el login del alumno que envió la evaluación del nombre del archivo
	print 'Procesando evaluación de '+alumno
	evaluacion = open(evaluacion) # Reasigno la variable :: De ser el "nombre del archivo" pasa a ser el archivo en sí!
	
	penalizacion = False
	razon = ''
	
	companeros = len(alumnos[alumno])-1
	puntosAsignar = 3*companeros
	
	for companero in evaluacion:
		if companero.startswith('PNLZD'): # Si el archivo se debió corregir a mano, la última línea empieza por PNLZD y separado por , la razón
			 penalizacion = True
			 razon += companero.split(',')[1]
			 penalizaciones[alumno] = razon
			 continue
		
		companeros -= 1	# Verificar que el número de compañeros coincida
		login = 'noLogin'
		puntos = -1
		
		if len(companero.split(',')) != 2: # Verificar que cada línea solo contenga dos elementos separados por coma
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: Más de dos elementos separados por , en '+companero
			penalizaciones[alumno] = razon
		login = companero.split(',')[0]
		
		try:
			puntos = float(companero.split(',')[1]) # Verificar que los puntos sean un número
		except ValueError:
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: puntos asignados a '+login+' no parseables a float: ' +companero.split(',')[1]
			penalizaciones[alumno] = razon
		
		if puntos < 0:
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: puntos asignados a '+login+' menor a cero: ' +str(puntos)
			penalizaciones[alumno] = razon
			
		if puntos < 2:
			advertencias.append(alumno+' asignó '+str(puntos)+' a '+login)
		
		if alumnos[alumno][login] != 3: # Verificar que el compañero pertenezca al grupo
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: compañero '+login+' no encontrado'
			penalizaciones[alumno] = razon
			
		if penalizacion:
			break
		else:
			alumnos[alumno][login] = puntos
			puntosAsignar -= puntos
			
	evaluacion.close()
		
	if not penalizacion:
		if companeros != 0:
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: Faltaron por calificar '+str(companeros)
			penalizaciones[alumno] = razon
		
		if puntosAsignar < 0:
			penalizacion = True
			razon += 'ERR :: '+alumno+' :: Se asignaron '+str(-puntosAsignar)+' de mas'
			penalizaciones[alumno] = razon
		
	if penalizacion:
		alumnos[alumno][alumno] = -0.5
	else:
		print 'Evaluación de '+alumno+' se procesó sin errores'
		alumnos[alumno][alumno] = 0
	print '  ', alumnos[alumno]
	print '  '+razon

print '\n ----------------------------------------------------------------- \n'		
for grupo, integrantes in grupos.items():
	print '\n'+grupo, ':: '+str(len(integrantes))+' integrantes'; 
	for integrante in integrantes:
		print '  ', integrante, alumnos[integrante]

print '\n ----------------------------------------------------------------- \n'
print 'PENALIZACIONES: \n'
pp.pprint(penalizaciones)		
	
print '\n ----------------------------------------------------------------- \n'
print 'ADVERTENCIAS: \n'
pp.pprint(advertencias)
	
