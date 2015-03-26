clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometr�a I\Taller1.Econometr�aI"
log using pto3-4.taller1.econometr�a1.smcl, replace
use taller1
**********
**PUNTO3**
**********
*
*a) Estimaci�n de coeficientes (Modelo1)
reg votos_d gastos_d
*-------------------
*Los coeficientes son iguales a los obtenidos en excel para el Modelo1
*
*(Modelo2)
reg votos_d gastos_r
*-------------------
*Los coeficientes son iguales a los obtenidos en excel para el Modelo2
*
*b)Gr�ficas
plot votos_d gastos_d
plot votos_d gastos_r
twoway scatter votos_d gastos_d || lfit votos_d gastos_d, saving(votos_dgastos_d, replace)
twoway scatter votos_d gastos_r || lfit votos_d gastos_r, saving(votos_dgastos_r, replace)
graph export votos_dgastos_d.emf, replace
graph export votos_dgastos_r.emf, replace
*
*c) Comparaci�n de r-squared
reg votos_d gastos_d
reg votos_r gastos_r
**R-squared(_r)=  0.1758
**R-squared(_d)=  0.1561
*-------------------
*El Modelo2 es mejor que el Modelo1 seg�n r-squared
*
**********
**PUNTO4**
**********
*
*a)Modelo para Cambio Porcentual
gen prvotos_d=100*log(votos_d)
plot prvotos_d gastos_d
reg prvotos_d gastos_d
*
*b)Modelo3: "Gastos de campa�a afectan exponencialmente el porcentaje de votos obtenidos"
gen expgastos_d=exp(gastos_d)
plot votos_d expgastos_d
reg votos_d expgastos_d
*-------------------
*Seg�n r-squared el Modelo3 es peor que el Modelo2
*
*c)Modelo para C�lculo de Elasticidad
gen lvotos_d=log(votos_d)
gen lgastos_d=log(gastos_d)
reg lgastos_d lvotos_d
*-------------------
*La elasticidad estimada de votos_d respecto a gastos_d es 2.993054

log close
