%% Taller 1 - Teor�a y Pr�ctica de Modelos DSGE
% Soluci�n del Modelo de Solow
% Camilo Vargas Cabrera
clc
clear all
close all
%% Par�metros

delta   = .025;             % Depreciaci�n
sigma   = .2;               % Tasa de ahorro
theta   = .3;               % Par�metro funci�n de producci�n
n       = (1+ .02)^(1/4);   % Tasa de crecimiento de la poblaci�n

% Nivel de capital inicial para el c�lculo de forma recursiva (90% del
% capital per c�pita de estado estacionario) calculado de forma anal�tica.
k0      = .9*((n+delta)/sigma)^(1/(theta-1));
%% C�lculo del estado estacionario de forma recursiva
kt(1,1)=k0;
yt(1,1)=kt(1,1).^theta;
for i=2:1000
    kt(i,1)=((1-delta)*kt(i-1,1)+sigma*yt(i-1,1))/(1+n);
    yt(i,1)=kt(i,1).^theta;
    if abs(kt(i,1)-kt(i-1,1))<1.e-10, break, end
end

figure(1)
x=1:i;
title 'Evoluci�n nivel de capital perc�pita'
plot(x,kt)

figure(2)
k=linspace(0,.4);   % Capital
y=k.^theta;         % Producto
s=sigma*y;          % Ahorro
x=(n+delta)*k;      % Depreciaci�n
title 'Estado estacionario'
plot(k,[y' s' x'])
legend ('Producto per c�pita','Ahorro per c�pita','Depreciaci�n per c�pita','Location','NorthWest')