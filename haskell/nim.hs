import Data.List(sort)
{-|
    Se modela el juego como un Integral (natural positivo de longitud arbitraria)
    donde cada dígito representa una pila
        |--> Limitado por construcción a pilas de hasta 9 fichas
        |--> Se ordenan las pilas siempre de menor a mayor número de fichas
-}

-- Calcula los sucesores de un estado del juego dado


-- Separa el juego en cada una de sus pilas
pilas :: Integral x => x -> [x]
pilas 0 = []
pilas x = pilas (div x 10) ++ [mod x 10]

-- Calcula las jugadas posibles para una pila dada
jugadas :: Integral x => x -> [x]
jugadas x = [1..x] -- puedo quitar desde 1 hasta x fichas de esta pila

-- Construye el -estado del- juego a partir de las pilas
juego :: Integral x => [x] -> x
juego []  = 0
juego [x] = sum $ zipWith (*) mult $ sort [x]
            where mult = [10^n | n <- [l-1..0]]
                  l = length [x]
