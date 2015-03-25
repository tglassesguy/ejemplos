#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n9_toDoList
# Autor: Carlos Navarrete Puentes - 24-feb-2011
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
javac -source 1.5 -classpath ../../lib/toDoList.jar:../lib/junit.jar -nowarn -d ../classes/ uniandes/cupi2/toDoList/test/*.java

# ---------------------------------------------------------
# Crea el archivo jar a partir de los archivos compilados
# ---------------------------------------------------------

cd ../classes
jar cf ../lib/toDoListTest.jar uniandes/* -C ../data .

cd ../../bin/mac

stty echo