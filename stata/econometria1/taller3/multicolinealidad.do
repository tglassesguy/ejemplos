capture log close
clear
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometr�a I\Taller3.Econometr�aI"
log using multicolinealidad.smcl, replace
use agro

* -----
* a)
* -----
reg  y mc mn ku kp t r l

* -----
* b)
* -----
corr mc mn ku kp t r l

* -----
* c)
* -----
reg mc mn ku kp t r l 
reg mn ku kp t r l mc
reg ku kp t r l mc mn
reg kp t r l mc mn ku
reg t r l mc mn ku kp
reg r l mc mn ku kp t 
reg l mc mn ku kp t r 





