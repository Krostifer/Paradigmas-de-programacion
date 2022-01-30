
%Clausulas
%Hechos

descendiente(abuelaAdams, tioCosa).
descendiente(abuelaAdams, tioLucas).
descendiente(abuelaAdams, homero).
descendiente(homero, merlina).
descendiente(homero, pubert).
descendiente(homero, pericles).
descendiente(morticia, merlina).
descendiente(morticia, pubert).
descendiente(morticia, pericles).

%Reglas

hermanos(A,B):- descendiente(X,A), descendiente(X,B), not(A = B).
tio(A, B):- descendiente(P, B), hermanos(P,A).