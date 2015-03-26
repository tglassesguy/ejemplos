function Y=nimopt(J)
Jopt=J;
j=mbin(J);          % j = Juego en forma binaria
s=nimsum(j);        % s = Suma Nim total del juego
for i=1:size(j,1)   % m = Suma Nim de cada fila con s
    m(i,:)=nimsum([s; j(i,:)]);
end
M=dec(m);           % M = Matriz m en decimal
A=M-J;              % A = Diferencia entre M y J
for i=1:size(j,1)   % Buscando fila que disminuye su tama�o
    if A(i)<0
        Jopt(i)=dec(m(i,:)); break  % Aplicando estrategia �ptima (Hacer la 
    end                             % fila en que A es negativo igual a dec(m(i,:))
end
if max(Jopt)>=2, Y=Jopt;            % Validando si es la �ltima jugada
                                    % Si queda alguna fila con 2 o m�s el juego
                                    % �ptimo es el calculado arriba.
else Y=J;                           % Si el juego �ptimo no fue el calculado
    for i=1:size(j,1)               % arriba (no queda ninguna fila con dos
        if J(i)>1, Y(i)=1; break   % o m�s), juego �ptimo es dejar 0 en la
        end                         % fila donde queden 2.
    end
end
if Y==J, disp('Juego en estado kernel, no existe una estrategia �ptima'),end
end