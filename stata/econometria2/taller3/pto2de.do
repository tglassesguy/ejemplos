clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometr�a II\taller3\pto2"
log using pto2d.smcl, replace
use murder
* ----------
* PUNTO D)
* ----------
*  Estimaci�n del modelo a trav�s de Primeras Diferencias
* ----------
reg cmrdrte cunem cexec d93, robust
* ----------
* PUNTO E)
* ----------
*  Estimaci�n del modelo a trav�s de Efectos Fijos
* ----------
xtreg mrdrte exec unem d90 d93, fe
* ----------
*  Estimaci�n del modelo a trav�s de Efectos Aleatorios
* ----------
xtreg mrdrte exec unem d93, re
* ----------
*  Estimaci�n del modelo a trav�s de MCO
* ----------
reg mrdrte exec unem
log close
