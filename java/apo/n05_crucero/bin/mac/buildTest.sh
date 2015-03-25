#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n5_crucero
# Autor: Catalina Rodr�guez - 16-sep-2010
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo
#SET CLASSPATH=

# ---------------------------------------------------------
# Asegura la creaci�n de los directorios classes y lib en test
# ---------------------------------------------------------

cd ../../test
mkdir classes
mkdir lib

# ---------------------------------------------------------
# Compila las clases del directotio test/source
# ---------------------------------------------------------

cd source
javac -source 1.5 -classpath ../../lib/crucero.jar;../../lib/flickrapi-1.2.jar;../lib/junit.jar -nowarn -d ../classes/ uniandes/cupi2/crucero/test/*.java

# ---------------------------------------------------------
# Crea el archivo jar a partir de los archivos compilados
# ---------------------------------------------------------

cd ../classes
jar cf ../lib/cruceroTest.jar uniandes/* -C ../data .

cd ../../bin/mac

stty echo