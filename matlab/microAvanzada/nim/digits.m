% Calcula el n�mero de d�gitos enteros de un n�mero
function y=digits(x)
for i=1:2147483647
    if x<10^(i-1)
        y=i-1; break
    end
end
end