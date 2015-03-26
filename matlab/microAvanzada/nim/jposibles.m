% Calcula todos los escenarios posibles para cada estado del juego. Depende
% de la funci�n jescalar y se encuentra limitado por �sta.
function j=jposibles(J)
if max(J)>9, disp('Este juego no se encuentra soportado: Tiene m�s de 9 elementos en alguna de las filas')
elseif sum(J)==0, j=0;
else
    for n=1:length(J)
        for m=1:J(n)
            Jfin=J;
            Jfin(n)=Jfin(n)-m;
            j(n,m)=jescalar(Jfin);
        end
    end
end
end