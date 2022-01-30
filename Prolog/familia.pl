persona(jose).
persona(jorge).
persona(nicolas).
persona(israel).
persona(paulina).
persona(dyllan).
persona(angel).
persona(karina).
persona(valeria).
persona(maria).
persona(juan).

padreDe(jose, nicolas).
padreDe(jose, angel).
padreDe(dyllan, jose).
padreDe(israel, karina).
padreDe(angel, valeria).
padreDe(nicolas, israel).

madreDe(karina, nicolas).
madreDe(karina, angel).
madreDe(paulina, valeria).

hermanosPaternos(X, Y) :- padreDe(P, X), 
    padreDe(P, Y), 
    not(X = Y).

hermanosMaternos(X, Y) :- madreDe(M, X), 
    madreDe(M, Y),
    not(X = Y).

hermanos(X, Y) :- hermanosPaternos(X, Y),
    not(hermanosMaternos(X, Y)).
    
hermanos(X, Y) :- hermanosMaternos(X, Y),
    not(hermanosPaternos(X, Y)).

hermanos(X, Y) :- hermanosCompletos(X, Y).
    
hermanosCompletos(X, Y) :- 
    hermanosPaternos(X, Y), 
    hermanosMaternos(X, Y).

tioDe(X, Y) :- padreDe(P, Y), hermanos(X, P).
tioDe(X, Y) :- madreDe(M, Y), hermanos(X, M).

abuelo(A, N) :- padreDe(P, N), padreDe(A, P).
abuelo(A, N) :- madreDe(M, N), padreDe(A, M).

abuela(A, N) :- padreDe(P, N), madreDe(A, P).
abuela(A, N) :- madreDe(M, N), madreDe(A, M).

primos(X, Y) :- padreDe(P1, X), 
    padreDe(P2, Y),
    hermanos(P1, P2).

antepasado(X, Y) :- padreDe(X, Y).
antepasado(X, Y) :- madreDe(X, Y).
antepasado(X, Y) :- padreDe(P, Y), antepasado(X, P).
antepasado(X, Y) :- madreDe(M, Y), antepasado(X, M).