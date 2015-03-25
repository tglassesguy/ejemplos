@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2
REM Ejercicio: n6_buscaminas
REM Autor: Mario S�nchez - 26-Jul-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM/

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creaci�n de los directorios classes y lib en test
REM ---------------------------------------------------------

cd ../test
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases del directotio test/source
REM ---------------------------------------------------------

cd source

javac -target 1.4 -source 1.4 -nowarn -classpath ../../lib/buscaminas.jar;../lib/junit.jar -d ../classes/ uniandes/cupi2/buscaminas/test/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ../classes

jar cf ../lib/buscaminasTest.jar uniandes/* -C ../data .

cd ../../bin

pause