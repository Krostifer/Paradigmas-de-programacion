/*
Plataforma: TDA que representa el catalogo de peliculas, consiste de una lista, donde cada elemento es una lista con las peliculas de cierto genero
E: Elemento a agregar en una lista
ColaG: Cola de la lista que contiene las peliculas de cierto genero
LF: Lista que contiene la lista a retornar
CabezaP: Cabeza del TDA plataforma
ColaP: Cola del TDA plataforma
Min: Entero que representa la duracion minima de una pelicula
Max: Entero que representa la duracion maxima de una pelicula
CabezaF: Cabeza de la lista final (lista a retornar)
ColaF: Cola de la lista final (lista a retornar)
ListaGenero: Lista que representa la sublista de un genero de peliculas
L: Lista cualquiera

Predicados

agregarAlFinal(L, E, LF)
crearPelicula(Titulo, Genero, Duracion, Restriccion, Pelicula)
buscarGenero(Plataforma, Genero, LF)
tituloUnico([ListaGenero, Titulo)
existeGenero(Plataforma, Genero)
agregarGenero(Plataforma, Genero, NuevaPlataforma)
pelicuaAlGenero(Plataforma, Pelicula, Genero, Plataforma2)
eliminarPeliculaGenero(ListaGenero, Titulo, Genero, NuevaListaGenero)
filtrarTiempo(ListaGenero, Min, Max, NuevaListaGenero)
agregarPelicula(Plataforma, Titulo, Genero, Duracion, Restriccion, Plataforma2)
eliminarPelicula(Plataforma, Genero, Titulo, Plataforma2)
filtrarPorDuracion(Plataforma, Min, Max, LF)

Metas
Principales: agregarPelicula, eliminarPelicula y filtrarPorDuracion.
*/

%Clausulas

%Reglas


agregarAlFinal([], E, [E]).
agregarAlFinal([Cabeza|Cola], E, [Cabeza|NuevaCola]) :- agregarAlFinal(Cola, E, NuevaCola).

crearPelicula(Titulo, Genero, Duracion, Restriccion, Pelicula):-
	Pelicula = [Titulo, Genero, Duracion, Restriccion].


buscarGenero([[Genero|ColaG]|_], Genero, [Genero|ColaG]):- !.
buscarGenero([_|ColaP], Genero, LF):- buscarGenero(ColaP, Genero, LF), !.

tituloUnico([[Titulo,_,_]|_], Titulo):- !.
tituloUnico([_|ColaG], Titulo):- tituloUnico(ColaG, Titulo), !.

existeGenero([[Genero|_]|_], Genero):- !.
existeGenero([_|Cola], Genero):- existeGenero(Cola,Genero), !.

agregarGenero(Plataforma, Genero, NuevaPlataforma):- NuevoGenero = [Genero], agregarAlFinal(Plataforma, NuevoGenero, NuevaPlataforma).

peliculaAlGenero([[Genero|ColaG]|ColaP], Pelicula, Genero, Plataforma2):- agregarAlFinal(ColaG, Pelicula, NuevaColaG), Plataforma2 = [[Genero|NuevaColaG]|ColaP], !.
peliculaAlGenero([CabezaP|ColaP], Pelicula, Genero, Plataforma2):- Plataforma2 = [CabezaP|NuevaColaP], peliculaAlGenero(ColaP, Pelicula, Genero, NuevaColaP), !.
	

eliminarPeliculaGenero([[Titulo,_,_]|ColaG], Titulo, _, ColaG).
eliminarPeliculaGenero([OtroTitulo|ColaG], Titulo, Genero, [OtroTitulo|NuevaColaG]):- eliminarPeliculaGenero(ColaG, Titulo, Genero, NuevaColaG), !.


filtrarTiempo([], _, _, []).
filtrarTiempo([[Titulo, Duracion, Restriccion]|Cola], Min, Max, [CabezaF|ColaF]):- 
	Duracion >= Min, Duracion =< Max, CabezaF = [Titulo, Duracion, Restriccion], filtrarTiempo(Cola, Min, Max, ColaF), !.
filtrarTiempo([_|Cola], Min, Max, LF):- filtrarTiempo(Cola, Min, Max, LF), !.



agregarPelicula(Plataforma, Titulo, Genero, Duracion, Restriccion, Plataforma2):-
	existeGenero(Plataforma, Genero),
	buscarGenero(Plataforma, Genero, ListaGenero),
	not(tituloUnico(ListaGenero, Titulo)), 
	crearPelicula(Titulo, Genero, Duracion, Restriccion, Pelicula),
	peliculaAlGenero(Plataforma, Pelicula, Genero, Plataforma2),!.

agregarPelicula(Plataforma, Titulo, Genero, Duracion, Restriccion, Plataforma2):-
	agregarGenero(Plataforma, Genero, PlataformaNueva),
	crearPelicula(Titulo, Genero, Duracion, Restriccion, Pelicula),
	peliculaAlGenero(PlataformaNueva, Pelicula, Genero, Plataforma2).

eliminarPelicula([[Genero, [Titulo,_,_]]|ColaP], Genero, Titulo, ColaP).
eliminarPelicula([[Genero|ColaG]|ColaP], Genero, Titulo, [NuevoGenero|ColaP]):- display([Genero|ColaG]),eliminarPeliculaGenero([Genero|ColaG], Titulo, Genero, NuevoGenero), !.
eliminarPelicula([CabezaP|ColaP], Genero, Titulo, [CabezaP|NuevaColaP]):- eliminarPelicula(ColaP, Genero, Titulo, NuevaColaP), !.

filtrarPorDuracion([], _, _, []).
filtrarPorDuracion([[_|ColaG]|ColaP], Min, Max, [CabezaF|ColaF]):-
	filtrarTiempo(ColaG, Min, Max, CabezaF), not(CabezaF = []), filtrarPorDuracion(ColaP, Min, Max, ColaF), !.
filtrarPorDuracion([_|ColaP], Min, Max, ColaF):- filtrarPorDuracion(ColaP, Min, Max, ColaF), !.