@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n2_bancoSangre
REM Autor: Catalina Rodr�guez - 11-ago-2010
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion del programa
REM ---------------------------------------------------------

cd ../..
java -ea -classpath ./lib/bancoSangre.jar uniandes.cupi2.bancoSangre.interfaz.InterfazBancoSangre
cd bin/win