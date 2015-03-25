#!/bin/sh

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n4_procesadoraCafe
# Autor: Catalina Rodr�guez - 01-sep-2010
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

#SET CLASSPATH=
# ---------------------------------------------------------
# Asegura la creaci�n del directorio docs/api
# ---------------------------------------------------------

cd ../../docs
mkdir api
cd ../bin/mac

# ---------------------------------------------------------
# Genera la documentaci�n
# ---------------------------------------------------------

javadoc -sourcepath ../source -d ../docs/api -subpackages uniandes.cupi2.procesadoraCafe

stty echo