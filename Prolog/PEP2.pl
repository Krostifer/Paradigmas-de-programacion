
%Hechos 
carrera(CodigoCarrera,NombreCarrera).
asignatura(CodigoAsignatura, Nombre, Nivel, CodigoPrerrequisito, CodigoCarrera).
profesor(RutProfesor, Nombre, Apellido, Experiencia).
%seccion(CodigoSeccion, RutProfesor, CodigoAsignatura, Capacidad).
%inscripcion(RutEstudiante, CodigoSeccion).
%calificacion(CodigoSeccion, RutEstudiante, Nota).

estudiante(12322434, "estudiante1", "Apellido1", 3).
estudiante(1222434, "estudiante2", "Apellido2", 2).
estudiante(1232234, "estudiante3", "Apellido3", 3).
estudiante(1232243, "estudiante4", "Apellido4", 1).
estudiante(1232234, "estudiante5", "Apellido5", 5).

seccion(1, 231323, 1, 30).

inscripcion(12322434, 1).
inscripcion(1222434, 1).
inscripcion(1232234, 1).
inscripcion(1232243, 1).
inscripcion(1232234, 1).

calificacion(1, 1, 6).
calificacion(1, 2, 5).
calificacion(1, 3, 6).
calificacion(1, 4, 2).
calificacion(1, 5, 6).

%Pregunta 2
%1) 
estudiante(_,Estudiante, _, CodigoInformatica).

%2)
profesorDe(NombreCarrera, Profesor):- 
	carrera(CodigoCarrera, NombreCarrera), 
	asignatura(CodigoAsignatura, _, _, _, CodigoCarrera),
	seccion(_, RutProfesor, CodigoAsignatura, _),
	profesor(RutProfesor, Profesor, _, _).

%3)Ya que no se especifica a que se refiere con prerrequisito, se asume que el codigo de prerrequisito hace referecia que el el estudiante
   %tenga una nota aprovatoria(mayor o igual a 4) en la asignatura con codigo de asignatura] igual al codigo de prerrequisito.
puedeInscribir(RutEstudiante, NombreAsignatura):-
	asignatura(_, NombreAsignatura, _, CodigoPrerrequisito,_),
	seccion(CodigoSeccion, _, CodigoPrerrequisito, _),
	calificacion(CodigoSeccion, RutEstudiante, Nota),
	Nota >= 4.


%4) 
mismaAsignatura(X, Y, NombreAsignatura):-
	asignatura(CodigoAsignatura, NombreAsignatura, _, _, _),
	seccion(CodigoSeccion, _, CodigoAsignatura, _),
	inscripcion(RutX, CodigoSeccion), 
	inscripcion(RutY, CodigoSeccion),
	not(RutX = RutY),
	estudiante(RutX, X, _, _),
	estudiante(RutY, Y, _, _).


%5) 
numeroMayor([], Mayor, Mayor).
numeroMayor([E|Cola], Mayor, X):- E > Mayor, numeroMayor(Cola, E, X), !.
numeroMayor([_|Cola], Mayor, X):- numeroMayor(Cola, Mayor, X), !.


mayorNota(CodigoSeccion, Rut):-
	findall(X, calificacion(_,_,X), ListaNotas),
	numeroMayor(ListaNotas, 0, MejorNota),
	calificacion(CodigoSeccion, Rut, MejorNota).

