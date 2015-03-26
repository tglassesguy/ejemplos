clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometr�a II\taller1"
log using 15.12.smcl,replace
*------------------------------
* Taller 1 - Econometr�a II
* Ejercicio 15.12 Wooldridge
* -----------------------------
* Manuel Godoy 	cod.200611480
* Camilo Vargas 	cod.200612197
*------------------------------
use wage2
*-----------
* 15.12 i)
*-----------
reg lwage sibs
*-----------
* Al comparar el estimador obtenido para sibs en la regresi�n anterior con el obtenido en el ejemplo 15.2, se observa que para la regresi�n del ejemplo, un aumento en un a�o de educaci�n aumenta en un 12.2% el nivel del salario; mientras que el efecto ceteris paribus del n�mero de hermanos sobre el nivel de salario es de -2.7%
*-----------
* 15.12 ii)
*-----------
reg educ brthord
*-----------
* La regresi�n de educaci�n contra orden de nacimiento muestra que por cada hermano adicional nacido antes, el nivel de educaci�n disminuye en 0.28 a�os. Este estimador es estad�sticamente significativo a un 5% de significancia ( -1.96 > -6.11)
*-----------
* 15.12 iii)
*-----------
ivreg lwage (educ=brthord), first
*-----------
* Se puede observar que la estimaci�n por MC2E utilizando a brthord como variable instrumental, un a�o de educaci�n adicional aumenta en un 13.06% el nivel de salario. El estimador para educaci�n estad�sticamente significativo a un 5% de significancia. 
*-----------
* 15.12 iv)
*-----------
*ho:Igualmente identificada
*h1:Sobreidentificada
*-----------
reg educ sibs brthord
predict ErroresPE, resid
reg lwage educ sibs ErroresPE
predict EMO, resid
reg EMO sibs brthord
scalar rcuadrado=e(r2)
scalar numeroobs=e(N)
scalar estadchi=rcuadrado*numeroobs
scalar list
*-----------
* No podemos rechazar Ho. 
*-----------
* Prueba de Hausman
*-----------
reg educ sibs brthord
predict ErroresE1, resid
reg lwage educ sibs ErroresE1
test ErroresE1
*-----------
* Podemos que ver educ es ex�gena.
*-----------
* 15.12 v)
*-----------
ivreg lwage (educ=brthord) sibs, first
*-----------
* Los errores est�ndar para los dos estimadores aumentan como consecuencia de la estimaci�n por MC2E (vs. la estimaci�n por MCO); se observa adem�s que la desviaci�n estandar de la variable instrumentada (educ) aumenta mucho m�s que la de la variable ex�gena sibs.
*-----------
* 15.12 vi)
*-----------
reg educ sibs brthord
predict educg
corr educg brthord
*-----------
* La alta correlaci�n se debe a que al utilizar a brthord como instrumento para educaci�n y a partir de ah� obtener educg, �sta termina siendo una combinaci�n lineal de brthord y las dem�s variables ex�genas, es por esto que la correlaci�n es tan alta
*-----------
