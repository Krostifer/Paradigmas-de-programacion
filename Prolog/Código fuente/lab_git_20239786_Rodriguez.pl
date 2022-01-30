/*
Definicion de los los TDA para la implementacion del problema
TDA stack = [listaUsuarios, listaPreguntas, listaRespuestas, usuarioActivo, IdUltimaPregunta, IdUltimaRespuesta]
TDA listaUsuarios = [[usuario1], [usuario2], [usuario3]]
TDA usuario = [nombre, contrasenia, [IDs preguntas], reputacion]
TDA listaPreguntas = [[pregunta1], [pregunta2], [pregunta3]]
TDA pregunta = [fecha, contenido, [etiquetas], ID, autor, estado, votos]
TDA listaRespuestas = [[respuesta1], [respuesta2], [respuesta3]]
TDA respuesta = [[fecha], IdPregunta, contenido, [etiquetas], autor, IdRespuesta, estado, votos]


Dominio 
Separador: String que se encontrara entre cada elemto de una lista al transfor esta en un largo string
StringLista: Largo string formado por los elementos de una lista
ID_P: Identificador de una pregunta si se encuentra en un TDA pregunta, si se encuentra en un TDA respuesta, es el identificador de la pregunta a la que va dirigida la respuesta
CambioReputacion: Entero equivalente a la cantidad de reputacion que se sumara al usuario
StringUsuario: Largo string con la informacion de un usuario
CambioVoto: Entero equivalente a la cantidad de votos que se sumara a la pregunta
StringPregunta: Largo string con la informacion de una pregunta y sus respectivas respuestas
ID_R: Identificador de una respuesta
StackInicial: TDA stack donde se desea consultar
StackResultante: Valor que toma un Stack al consultar un predicado
StackStr: Largo string con la informacion de un stack
TipoVoto: Booleano que representa si el voto a realizar es positivo(true) o negativo(false)
Temp1 - Temp9: Variables temporales para "almacenar" strings parte de una gran string 

Predicados

listaTostring(Lista, Separador, StringLista)
agregarAlFinal(Lista, ElementoAgregar, ListaModificada)
existeElemento(Lista, Elemento)
getNuevoIdPregunta(UltimoIdPregunta, NuevoIdPregunta)
getNuevoIdRespuesta(UltimoIdRespuesta, NuevoIdRespuesta)
hayLogin(UsuarioActivo, UsuarioActivo)
usuarioPorNombre(Usuario, Username, UsuarioBuscado)
puedoRegister(ListaUsuarios, Username)
puedoLogin(ListaUsuarios, Username, Pass, Usuario)
mapUsuariosString(ListaUsuarios, ListaFinal)
crearUsuario(Username, Pass, IDs, Reputacion, Usuario)
usuarioGetUsername(Usuario, Username)
esAutorP(Usuario, ID_P)
usuarioSetPregunta(Usuario, ID_P, UsuarioModificado)
usuarioSetReputacion(Usuario, CambioReputacion, UsuarioModificado)
modificarUsuarioPorNombre(ListaUsuarios, Username, NuevoUsuario, ListaUsuariosModificada)
usuarioTostring(Usuario, StringUsuario)
crearPregunta(Fecha, Contenido, Etiquetas, ID_P, Autor, Estado, Votos, Pregunta)
preguntaGetID(Pregunta, ID_P)
preguntaGetAutor(Pregunta, AutorPregunta)
preguntaSetEstado(Pregunta, NuevoEstado, PreguntaModificada)
preguntaSetVotos(Pregunta, CambioVoto, PreguntaModificada)
esPregunta(Pregunta)
preguntaTostring(Pregunta, ListaRespuestas, StringPregunta):- 
existePregunta(ListaPreguntas, ID_P)
preguntaPorID(ListaPreguntas, ID_P, Pregunta)
filtrarPorAutor(ListaPreguntas, AutorPregunta, ListaFiltrada)
getQuestion(Stack, ID_P, Pregunta)
modificarPreguntaPorID(ListaPreguntas, ID_P, NuevaPregunta, ListaPreguntasModificada)
mapPreguntasString(ListaPreguntas, ListaRespuestas, ListaStringPreguntas)
crearRespuesta(Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos, Respuesta)
respuestaGetID(Respuesta, ID_R)
respuestaGetAutor(Respuesta, AutorRespuesta)
respuestaSetEstado(Respuesta, NuevoEstado, RespuestaModificada)
respuestaSetVotos(Respuesta, CambioVoto, RespuestaModificada)
esRespuesta(Respuesta)
respuestaTostring(Respuesta, StringRespuesta)
respuestaPorID(ListaRespuestas, ID_P, ID_R, Respuesta)
filtrarAnswer(ListaRespuestas, ID_P, ListaFiltrada)
getAnswer(Stack, ID_P, ID_R, Respuesta)
modificarRespuestaPorID(ListaRespuestas, ID_R, NuevaRespuesta, ListaRespuestasModificada)
mapRepuestasString(ListaRespuestas], ListaStringRespuestas)
stackRegister(StackInicial, Username, Pass, StackResultante)
stackLogin(StackInicial, Username, Pass, StackResultante)
ask(StackInicial, Fecha, Contenido, Etiquetas, StackResultante)
answer(StackInicial, Fecha, ID_P, Contenido, Etiquetas, StackResultante)
accept(StackInicial, ID_P, ID_R, StackResultante)
stackTostring(StackInicial, StackStr)
vote(StackInicial, Pregunta, TipoVoto, StackResultante)


Metas
Principales: stackRegister, stackLogin, ask, answer, accept, stackTostring, vote, getQuestion y getAnswer.

Clausulas
Hechos
*/

%Stack de ejemplo 1
stack([
	[
	 ["chris", "chris123", [1], 120], 
	 ["jaime", "456", [2], 50], 
	 ["joel", "jou6", [5], 0], 
	 ["jose", "jose654", [3,4], 10]
	], 
	[
	 ["24-11-2020", "Como usar Prolog?", ["Prolog"], 1, "jaime", "Cerrada", 6],
	 ["24-11-2020", "Que es la unificacion ?", ["Prolog", "Unificacion"], 2, "chris", "Abierta", 5], 
	 ["24-11-2020", "Que es backtracking ?", ["Prolog"], 3, "jose", "Abierta", 3], 
	 ["24-11-2020", "Como instalo Prolog ?", ["Prolog"], 4, "jose", "Abierta", 0],
	 ["24-11-2020", "Por que no veo la lista completa ?", ["Prolog", "SWI-Prolog"], 5, "joel", "Abierta", 1]
	], 
	[
	 ["25-11-2020", 1, "Revisa mis tutoriales en Youtube", ["Prolog"], "joel", 1, " ", 5], 
	 ["26-11-2020", 2, "Una caracteristica de Prolog", ["Prolog"], "jaime", 2, " ", 1],
	 ["27-11-2020", 1, "Lee la documentacion", ["No", "Quizas"], "chris", 3, "Aceptada", 0],
	 ["28-11-2020", 3, "Busca en Google", ["Prolog"], "chris", 4, " ", 5],
	 ["28-11-2020", 4, "Busca SWI-Prolog", ["SWI-Prolog", "Prolog"], "jose", 5, " ", 10],
	 ["28-11-2020", 3, "No se", ["Prolog"], "joel", 6, " ", -1],
	 ["28-11-2020", 5, "Problemas de tu pc", ["Prolog"], "jose", 7, " ", 0],
	 ["28-11-2020", 5, "Busca en otro foro", ["ayuda"], "joel", 8, " ", -2],
	 ["28-11-2020", 2, "Una maravilla para buscar cosas en Prolog", ["Prolog"], "jose", 9, " ", 0],
	 ["28-11-2020", 4, "Existe un invento maravilloso llamado Google", ["ayuda"], "chris", 10, " ", 0]
	],
	[],  
	5, 
	10
	], 1).

%Stack de ejemplo 2 (Stack vacio)
stack([[], [], [], [], 0, 0], 2).

%Reglas

%******** General **************

%Genera un largo string con los elementos de una lista separados por un separador entregado
listaTostring([], _,  "").
listaTostring([Cabeza|Cola], Separador, StringLista):- string_concat(Cabeza, Separador, Temp1), listaTostring(Cola, Separador, StringCola), string_concat(Temp1, StringCola, StringLista).

%Agrega un elemento al final de una lista
agregarAlFinal([], ElementoAgregar, [ElementoAgregar]).
agregarAlFinal([Cabeza|Cola], ElementoAgregar, [Cabeza|NuevaCola]) :- agregarAlFinal(Cola, ElementoAgregar, NuevaCola).

%Comprueba que exista cirto elemento dentro de una lista entregada
existeElemento([Elemento], Elemento).
existeElemento([Elemento|_], Elemento) :- !.
existeElemento([_|Cola], Elemento) :- existeElemento(Cola, Elemento), !.

%******** TDA Stack **************

%SELECTORES

%Obtiene el ID para la nueva pregunta a registrar dentro del stack
getNuevoIdPregunta(UltimoIdPregunta, NuevoIdPregunta):- number(UltimoIdPregunta), NuevoIdPregunta is (UltimoIdPregunta + 1).

%Obtiene el ID para la nueva respuesta a registrar dentro del stack
getNuevoIdRespuesta(UltimoIdRespuesta, NuevoIdRespuesta):- number(UltimoIdRespuesta), NuevoIdRespuesta is (UltimoIdRespuesta + 1).

%OTRAS

%Comprueba que el usuario activo de un stack, sea un usuario y no una lista vacia(sin sesion iniciada), retornando al usuario con sesion iniciada si es que lo hay
hayLogin(UsuarioActivo, UsuarioActivo):- not(UsuarioActivo == []).


%******** TDA lista usuarios **************

%SELECTORES

%Obtiene un usuario de una lista de usuarios, dicho usuario debe tener el mismo nombre que el entregado
usuarioPorNombre([Username, Pass, Preguntas, Reputacion], Username, [Username, Pass, Preguntas, Reputacion]).
usuarioPorNombre([[Username, Pass, Preguntas, Reputacion]|_], Username, [Username, Pass, Preguntas, Reputacion]).
usuarioPorNombre([_|Cola], Username, U) :- usuarioPorNombre(Cola, Username, U).

%Comprueba que no exista un usuario registrado con el nombre de usuario que se desea registrar
puedoRegister([[Username, _, _, _]], Username).
puedoRegister([[Username, _, _, _]|_], Username).
puedoRegister([_|Cola], Username) :- puedoRegister(Cola, Username), !.

%Comprueba que los datos con los que se desea iniciar sesion correspondan a los datos de un usuario ya registrado
puedoLogin([[Username, Pass, IDsPreguntas, Reputacion]], Username, Pass, [Username, Pass, IDsPreguntas, Reputacion]):- !.
puedoLogin([[Username, Pass, IDsPreguntas, Reputacion]|_], Username, Pass, [Username, Pass, IDsPreguntas, Reputacion]):- !.
puedoLogin([_|Cola], Username, Pass, Usuario) :- puedoLogin(Cola, Username, Pass, Usuario), !.

%OTRAS

%Aplica el predicado usuarioTostring a cada usuario presente en una lista de usuarios
mapUsuariosString([], []).
mapUsuariosString([Cabeza|Cola], [StringCabeza|StringCola]):- usuarioTostring(Cabeza, StringCabeza), mapUsuariosString(Cola, StringCola).

%******** TDA usuario **************

%CONSTRUCTOR

%Crea un TDA usuario con los datos entregados una vez comprobado que los datos cumplan con su respectivo tipo de dato
crearUsuario(Username, Pass, IDs, Reputacion, [Username, Pass, IDs, Reputacion]):- string(Username), string(Pass), is_list(IDs), number(Reputacion).

%SELECTORES

%Obtiene el nombre de usuario de un TDA usuario
usuarioGetUsername([Username, _, _, _], Username).

%MODIFICADORES

%Agrega el ID de una preguna realizada por un usuario a la lista de IDs de preguntas
usuarioSetPregunta([Username, Pass, IDsPreguntas, Reputacion], ID, [Username, Pass, NuevosIDsPreguntas, Reputacion]):- agregarAlFinal(IDsPreguntas, ID, NuevosIDsPreguntas).

%Suma un valor entregado a la reputacion de un usuario
usuarioSetReputacion([Username, Pass, IDsPreguntas, Reputacion], CambioReputacion, [Username ,Pass, IDsPreguntas, NuevaReputacion]):- number(CambioReputacion), NuevaReputacion is (Reputacion + CambioReputacion).

%Reemplaza un usuario dentro de una lista de usuario por una version actualizada de el
modificarUsuarioPorNombre([[Username, _, _, _]|Cola], Username, NuevoUsuario, [NuevoUsuario|Cola]).
modificarUsuarioPorNombre([Cabeza|Cola], Nombre, NuevoUsuario, [Cabeza|NuevaCola]):- modificarUsuarioPorNombre(Cola, Nombre, NuevoUsuario, NuevaCola), !.
modificarUsuarioPorNombre(ListaUsuarios, _, _, ListaUsuarios).

%OTRAS

%Comprueba que un usuario sea el autor de una pregunta por medio de su identificador
esAutorP([_, _, IDsPreguntas, _], ID_P):- existeElemento(IDsPreguntas, ID_P), !.

%Genera un largo string que contiene la informacion de un usuario
usuarioTostring([Username, Pass, IDs, Reputacion], StringUsuario):-
	string_concat("Nombre: ", Username, UsernameString),
	string_concat("      Contrasenia: ", Pass, PassString),
	string_concat("\nReputacion: ", Reputacion, RepString),
	listaTostring(IDs, " - ", IDsP),
	string_concat("      IDs preguntas: ", IDsP, IDsString),
	string_concat(UsernameString, PassString, Temp1),
	string_concat(Temp1, RepString, Temp2),
	string_concat(Temp2, IDsString, StringUsuario).

%********* TDA pregunta ******************

%CONSTRUCTOR

%Crea un TDA pregunta con la informacion entregada una vez se compueba que cada elemento cumple con su respectivo tipo de dato
crearPregunta(Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos, [Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]):-
	string(Fecha),
	string(Contenido),
	is_list(Etiquetas),
	number(ID),
	string(Autor),
	string(Estado),
	number(Votos).

%SELECTORES

%Obtiene el identificador de la pregunta entregada
preguntaGetID([_, _, _, ID, _, _, _], ID).

%Obtiene el autor de la pregunta entregada
preguntaGetAutor([_,_,_,_,Autor,_,_], Autor).

%MODIFICADORES

%Cambia el estado de una pregunta de Abierta a Cerrada
preguntaSetEstado([Fecha, Contenido, Etiquetas, ID, Autor, _, Votos], NuevoEstado, [Fecha, Contenido, Etiquetas, ID, Autor, NuevoEstado, Votos]):- string(NuevoEstado).

%Suma cierta cantidad a los votos de una pregunta
preguntaSetVotos([Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos], CambioVoto, [Fecha, Contenido, Etiquetas, ID, Autor, Estado, NuevosVotos]):- number(CambioVoto), NuevosVotos is (Votos + CambioVoto).

%PERTENENCIA

%Comprueba que el elemento entregado cumpla con la estructura de un TDA pregunta
esPregunta([Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]):-
	string(Fecha),
	string(Contenido),
	is_list(Etiquetas),
	number(ID),
	string(Autor),
	string(Estado),
	number(Votos).

%OTRAS

%Genera un largo string con la informacion de una pregunta y sus respectivas respuestas 
preguntaTostring([Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos], ListaRespuestas, StringPregunta):- 
	string_concat("\nPregunta: ", ID, IdString), 
	string_concat("   Fecha: ", Fecha, FechaString),
	string_concat("   Votos: ", Votos, VotosString),
	string_concat("   Estado: ", Estado, EstadoString),
	string_concat("Autor: ", Autor, AutorString),
	listaTostring(Etiquetas, " - ", EtiquetasS),
	string_concat("      Etiquetas: ", EtiquetasS, EtiquetasString),
	string_concat(IdString, FechaString, Temp1),
	string_concat(Temp1, VotosString, Temp2),
	string_concat(Temp2, EstadoString, Temp3),
	string_concat(Temp3, "\n   ", Temp4),
	string_concat(Temp4, Contenido, Temp5),
	string_concat(Temp5, "\n   ", Temp6),
	string_concat(Temp6, AutorString, Temp7),
	string_concat(Temp7, EtiquetasString, Temp8),
	string_concat(Temp8, "\n\n", Temp9),
	filtrarAnswer(ListaRespuestas, ID, Respuestas),
	mapRepuestasString(Respuestas, RespuestasLString),
	listaTostring(RespuestasLString, "\n\n", RespuestasString),
	string_concat(Temp9, RespuestasString, StringPregunta).

	

%********* TDA lista preguntas ******************

%SELECTORES

%Obtiene una pregunta de una lista de preguntas por medio de su identificador
preguntaPorID([Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos], ID, [Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]).
preguntaPorID([[Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]|_], ID, [Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]).
preguntaPorID([_|Cola], ID, Pregunta):- preguntaPorID(Cola, ID, Pregunta).

%Entrega una lista con todas las preguntas realizadas por cierto usuario
filtrarPorAutor([], _, []).
filtrarPorAutor([[Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos]|Cola], Autor, [CabezaFiltrada|ColaFiltrada]):-
	CabezaFiltrada = [Fecha, Contenido, Etiquetas, ID, Autor, Estado, Votos], filtrarPorAutor(Cola, Autor, ColaFiltrada), !.
filtrarPorAutor([_|Cola], Autor, ListaFiltrada):- filtrarPorAutor(Cola, Autor, ListaFiltrada), !.

%Obtiene una pregunta de un stack por medio de su identificador
getQuestion([_,ListaPreguntas,_,_,_,_], ID, Pregunta):- preguntaPorID(ListaPreguntas, ID, Pregunta), !.

%MODIFICADORES

%Reemplaza una pregunta por una version actualizada de ella y retorna la nueva lista de preguntas
modificarPreguntaPorID([[_, _, _, ID, _, _, _]|Cola], ID, NuevaPregunta, [NuevaPregunta|Cola]).
modificarPreguntaPorID([Cabeza|Cola], ID, NuevaPregunta, [Cabeza|NuevaCola]):- modificarPreguntaPorID(Cola, ID, NuevaPregunta, NuevaCola), !.
modificarPreguntaPorID(ListaPreguntas, _, _, ListaPreguntas).

%OTRAS

%Comprueba que exista una pregunta registrada con el identificador entregado
existePregunta([_, _, _, ID, _, _, _], ID).
existePregunta([[_, _, _, ID, _, _, _]|_], ID).
existePregunta([_|Cola], ID):- existePregunta(Cola, ID).

%Aplica el predicado preguntaTostring a cada pregunta presente en una lista de preguntas
mapPreguntasString([], _, []).
mapPreguntasString([Cabeza|Cola], Respuestas, [StringCabeza|StringCola]):- preguntaTostring(Cabeza, Respuestas, StringCabeza), mapPreguntasString(Cola, Respuestas, StringCola).


%********* TDA respuesta ******************

%CONSTRUCTOR

%Crea un TDA respuesta una vez se comprueba que cada elemento cumple con su respectivo tipo de dato
crearRespuesta(Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos, [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]):-
	string(Fecha),
	number(ID_P),
	string(Contenido),
	is_list(Etiquetas),
	string(Autor),
	number(ID_R),
	string(Estado),
	number(Votos).

%SELECTORES

%Obtiene el identificador de una respuesta
respuestaGetID([_, _, _, _, _, ID, _, _], ID).

%Obtiene el autor de una respuesta
respuestaGetAutor([_, _, _, _, Autor, _, _, _], Autor).

%*MODIFICADORES

%Cambia el estado de una respuesta a aceptada
respuestaSetEstado([Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, _, Votos], NuevoEstado, [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, NuevoEstado, Votos]):- string(NuevoEstado).

%Suma cierta cantidad de votos a los votos de una respuesta
respuestaSetVotos([Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos], CambioVoto, [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, NuevosVotos]):- number(CambioVoto), NuevosVotos is (Votos + CambioVoto).

%PERTENENCIA

%Comprueba que el elemento entregado cumple con la estructura de un TDA respuesta
esRespuesta([Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]):-
	string(Fecha),
	number(ID_P),
	string(Contenido),
	is_list(Etiquetas),
	string(Autor),
	number(ID_R),
	string(Estado),
	number(Votos).

%OTRAS

%Genera un largo string con la informacion de una respuesta
respuestaTostring([Fecha, _, Contenido, Etiquetas, Autor, ID_R, Estado, Votos], RespuestaString):-
	string_concat("      Respuesta: ", ID_R, IdString), 
	string_concat("   Fecha: ", Fecha, FechaString),
	string_concat("   Votos: ", Votos, VotosString),
	string_concat("   Estado: ", Estado, EstadoString),
	string_concat("Autor: ", Autor, AutorString),
	listaTostring(Etiquetas, " - ", EtiquetasS),
	string_concat("      Etiquetas: ", EtiquetasS, EtiquetasString),
	string_concat(IdString, FechaString, Temp1),
	string_concat(Temp1, VotosString, Temp2),
	string_concat(Temp2, EstadoString, Temp3),
	string_concat(Temp3, "\n         ", Temp4),
	string_concat(Temp4, Contenido, Temp5),
	string_concat(Temp5, "\n         ", Temp6),
	string_concat(Temp6, AutorString, Temp7),
	string_concat(Temp7, EtiquetasString, RespuestaString).  

%********* TDA lista respuestas ******************

%SELECTORES

%Obtiene una respuesta de una lista de respuestas por medio de su identificador
respuestaPorID([[Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]], ID_P, ID_R, [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]).
respuestaPorID([[Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]|_], ID_P, ID_R, [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]).
respuestaPorID([_|Cola], ID_P, ID_R, Respuesta):- respuestaPorID(Cola, ID_P, ID_R, Respuesta).

%Filtra una lista de respuestas y restorna una lista de respuestas compuesta solo por respuestas que van dirigidas a cierta pregunta por su identificador
filtrarAnswer([], _, []).
filtrarAnswer([[Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos]|Cola], ID_P, [CabezaFiltrada|ColaFiltrada]):-
	CabezaFiltrada = [Fecha, ID_P, Contenido, Etiquetas, Autor, ID_R, Estado, Votos], filtrarAnswer(Cola, ID_P, ColaFiltrada), !.
filtrarAnswer([_|Cola], ID_P, ListaFiltrada):- filtrarAnswer(Cola, ID_P, ListaFiltrada), !.

%Obtiene una respuesta que responde a cierta pregunta de un stack por medio de su identificador
getAnswer([_, _, Respuestas, _, _, _], ID_P, ID_R, Respuesta):- respuestaPorID(Respuestas, ID_P, ID_R, Respuesta), !.


%MODIFICADORES

%Reemplaza una respuesta por una version actualizada de ella y retorna una lista de respuestas actalizada
modificarRespuestaPorID([[_, _, _, _, _, ID_R, _, _]|Cola], ID_R, NuevaRespuesta, [NuevaRespuesta|Cola]).
modificarRespuestaPorID([[_, _, _, _, _, ID_R, _, _]|Cola], ID_R, NuevaRespuesta, [NuevaRespuesta|NuevaCola]):- modificarRespuestaPorID(Cola, ID_R, NuevaRespuesta, NuevaCola), !.
modificarRespuestaPorID(ListaRespuestas, _, _, ListaRespuestas).

%OTRAS

%Aplica el predicado respuestaTostring a cada respuesta presente en una lista de respuestas
mapRepuestasString([], []).
mapRepuestasString([Cabeza|Cola], [StringCabeza|StringCola]):- respuestaTostring(Cabeza, StringCabeza), mapRepuestasString(Cola, StringCola).


%************************************* FUNCIONALES ***********************************************

%Registra a un usuario dentro de un stack
stackRegister([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta], Username, Pass, [NuevosUsuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta]):-
	string(Username),
	string(Pass),
	not(puedoRegister(Usuarios, Username)),
	crearUsuario(Username, Pass, [], 0, Usuario),
	agregarAlFinal(Usuarios, Usuario, NuevosUsuarios).

%Inicia la sesion de un usuario previamente registrado
stackLogin([Usuarios, Preguntas, Respuestas, _, IdUltimaPregunta, IdUltimaRespuesta], Username, Pass, [Usuarios, Preguntas, Respuestas, NuevoUsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta]):-
	string(Username),
	string(Pass),
	puedoLogin(Usuarios, Username, Pass, NuevoUsuarioActivo).

%Crea un pregunta y la registra dentro de un stack
ask([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta], Fecha, Contenido, Etiquetas, [NuevosUsuarios, NuevasPreguntas, Respuestas, [], NuevoIdPregunta, IdUltimaRespuesta]):-
	hayLogin(UsuarioActivo, UsuarioAutorPregunta),
	usuarioGetUsername(UsuarioAutorPregunta, AutorPregunta),
	getNuevoIdPregunta(IdUltimaPregunta, NuevoIdPregunta),
	crearPregunta(Fecha, Contenido, Etiquetas, NuevoIdPregunta, AutorPregunta, "Abierta", 0, NuevaPregunta),
	agregarAlFinal(Preguntas, NuevaPregunta, NuevasPreguntas),
	usuarioSetPregunta(UsuarioAutorPregunta, NuevoIdPregunta, NuevoUsuario),
	modificarUsuarioPorNombre(Usuarios, AutorPregunta, NuevoUsuario, NuevosUsuarios), !.
	
%Crea una respuesta y la registra dentro de un stack
%Caso donde no hay un usuario con sesion iniciada
answer([_, _, _, UsuarioActivo, _, _], _, _, _, _, _):- not(hayLogin(UsuarioActivo, _)), !, fail.
%Caso donde no exite la pregunta que se desea responder
answer([_, Preguntas, _, _, _, _], _, ID_P, _, _, _):- not(existePregunta(Preguntas, ID_P)), !, fail.
%Caso general
answer([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta], Fecha, ID_P, Contenido, Etiquetas, [Usuarios, Preguntas, NuevasRespuestas, [], IdUltimaPregunta, NuevoIdRespuesta]):-
	getNuevoIdRespuesta(IdUltimaRespuesta, NuevoIdRespuesta),
	usuarioGetUsername(UsuarioActivo, AutorRespuesta),
	crearRespuesta(Fecha, ID_P, Contenido, Etiquetas, AutorRespuesta, NuevoIdRespuesta, " ", 0, NuevaRespuesta),
	agregarAlFinal(Respuestas, NuevaRespuesta, NuevasRespuestas).

%Marca una respuesta como la mejor respuesta a una pregunta del usuario con sesion iniciada
%Caso en donde no hay usuario activo
accept([_, _, _, UsuarioActivo, _, _], _, _, _):- not(hayLogin(UsuarioActivo, _)), !, fail.
%Caso cuando el usuario con sesion iniciada no es el autor de la pregunta indicada
accept([_, _, _, UsuarioActivo, _, _], ID_P, ID_R, _):- number(ID_P), number(ID_R), not(esAutorP(UsuarioActivo, ID_P)), !, fail.
%Caso general
accept([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaPregunta, IdUltimaRespuesta], ID_P, ID_R, [NuevosUsuariosFinal, NuevasPreguntas, NuevasRespuestas, [], IdUltimaPregunta, IdUltimaRespuesta]):- 
	preguntaPorID(Preguntas, ID_P, Pregunta),
	preguntaSetEstado(Pregunta, "Cerrada", NuevaPregunta),
	modificarPreguntaPorID(Preguntas, ID_P, NuevaPregunta, NuevasPreguntas),
	respuestaPorID(Respuestas, ID_P, ID_R, Respuesta),
	respuestaSetEstado(Respuesta, "Aceptada", NuevaRespuesta),
	modificarRespuestaPorID(Respuestas, ID_R, NuevaRespuesta, NuevasRespuestas),
	usuarioSetReputacion(UsuarioActivo, 2, NuevoUsuarioAceptador),
	usuarioGetUsername(NuevoUsuarioAceptador, AutorAceptar),
	modificarUsuarioPorNombre(Usuarios, AutorAceptar, NuevoUsuarioAceptador, NuevosUsuarios),
	respuestaGetAutor(Respuesta, AutorRespuesta),
	usuarioPorNombre(NuevosUsuarios, AutorRespuesta, UsuarioAutorRespuesta),
	usuarioSetReputacion(UsuarioAutorRespuesta, 15, NuevoUsuarioAutorRespuesta),
	modificarUsuarioPorNombre(NuevosUsuarios, AutorRespuesta, NuevoUsuarioAutorRespuesta, NuevosUsuariosFinal), !.

%Genera un largo string con la informacion de un stack
%Caso donde existe un usuario con sesion iniciada
stackTostring([_, Preguntas, Respuestas, UsuarioActivo, _, _], StackStr):- hayLogin(UsuarioActivo, UsuarioLoggueado),
	usuarioGetUsername(UsuarioLoggueado, UsernameUsuarioLoggueado),
	filtrarPorAutor(Preguntas, UsernameUsuarioLoggueado, PreguntasUsuarioLoggueado),
	mapPreguntasString(PreguntasUsuarioLoggueado, Respuestas, ListaStringPreguntas),
	listaTostring(ListaStringPreguntas, "\n", StringPreguntas),
	usuarioTostring(UsuarioLoggueado, StringUsuarioLoggueado),
	string_concat(StringUsuarioLoggueado, "\n\n", Temp1),
	string_concat(Temp1, "Preguntas del usuario:\n", Temp2),
	string_concat(Temp2, StringPreguntas, StackStr), !.

%Caso general donde no hay un usuario con sesion iniciada
stackTostring([Usuarios, Preguntas, Respuestas, _, _, _], StackStr):-
	mapPreguntasString(Preguntas, Respuestas, ListaStringPreguntas),
	listaTostring(ListaStringPreguntas, "\n", StringPreguntas),
	mapUsuariosString(Usuarios, ListaStringUsuarios),
	listaTostring(ListaStringUsuarios, "\n\n", StringUsuarios), 
	string_concat("***STACK OVERFLOW***\n", StringPreguntas, Temp1),
	string_concat(Temp1, "*Usuarios*\n", Temp2),
	string_concat(Temp2, StringUsuarios, StackStr).

%Un con sesion inciada realiza un voto a una pregunta o respuesta
%Vote positivo a pregunta
vote([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaP, IdUltimaR], Pregunta, Voto, [NuevosUsuarios, NuevasPreguntas, Respuestas, [], IdUltimaP, IdUltimaR]):- 
	hayLogin(UsuarioActivo, _),
	esPregunta(Pregunta),
	Voto,
	preguntaGetAutor(Pregunta, AutorPregunta),
	preguntaGetID(Pregunta, ID),
	usuarioPorNombre(Usuarios, AutorPregunta, UsuarioAutorPregunta),
	usuarioSetReputacion(UsuarioAutorPregunta, 10, NuevoUsuarioAutorPregunta),
	modificarUsuarioPorNombre(Usuarios, AutorPregunta, NuevoUsuarioAutorPregunta, NuevosUsuarios),
	preguntaSetVotos(Pregunta, 1, NuevaPregunta),
	modificarPreguntaPorID(Preguntas, ID, NuevaPregunta, NuevasPreguntas), !.

%Vote negativo a pregunta
vote([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaP, IdUltimaR], Pregunta, Voto, [NuevosUsuariosFinal, NuevasPreguntas, Respuestas, [], IdUltimaP, IdUltimaR]):-
	hayLogin(UsuarioActivo, _),
	esPregunta(Pregunta),
	Voto == false,
	preguntaGetAutor(Pregunta, AutorPregunta),
	usuarioGetUsername(UsuarioActivo, AutorVoto),
	preguntaGetID(Pregunta, ID),
	usuarioSetReputacion(UsuarioActivo, -1, NuevoUsuarioActivo),
	modificarUsuarioPorNombre(Usuarios, AutorVoto, NuevoUsuarioActivo, NuevosUsuarios),
	usuarioPorNombre(NuevosUsuarios, AutorPregunta, UsuarioAutorPregunta),
	usuarioSetReputacion(UsuarioAutorPregunta, -2, NuevoUsuarioAutorPregunta),
	modificarUsuarioPorNombre(NuevosUsuarios, AutorPregunta, NuevoUsuarioAutorPregunta, NuevosUsuariosFinal),
	preguntaSetVotos(Pregunta, -1, NuevaPregunta),
	modificarPreguntaPorID(Preguntas, ID, NuevaPregunta, NuevasPreguntas),!.

%Vote positivo a respuesta
vote([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaP, IdUltimaR], Respuesta, Voto, [NuevosUsuarios, Preguntas, NuevasRespuestas, [], IdUltimaP, IdUltimaR]):-
	hayLogin(UsuarioActivo, _),
	esRespuesta(Respuesta),
	Voto,
	respuestaGetAutor(Respuesta, AutorRespuesta),
	respuestaGetID(Respuesta, ID),
	usuarioPorNombre(Usuarios, AutorRespuesta, UsuarioAutorRespuesta),
	usuarioSetReputacion(UsuarioAutorRespuesta, 10, NuevoUsuarioAutorRespuesta),
	modificarUsuarioPorNombre(Usuarios, AutorRespuesta, NuevoUsuarioAutorRespuesta, NuevosUsuarios),
	respuestaSetVotos(Respuesta, 1, NuevaRespuesta),
	modificarRespuestaPorID(Respuestas, ID, NuevaRespuesta, NuevasRespuestas), !.

%Vote negativo a respuesta
vote([Usuarios, Preguntas, Respuestas, UsuarioActivo, IdUltimaP, IdUltimaR], Respuesta, Voto, [NuevosUsuariosFinal, Preguntas, NuevasRespuestas, [], IdUltimaP, IdUltimaR]):-
	hayLogin(UsuarioActivo, _),
	esRespuesta(Respuesta),
	Voto == false,
	respuestaGetAutor(Respuesta, AutorRespuesta),
	usuarioGetUsername(UsuarioActivo, AutorVoto),
	respuestaGetID(Respuesta, ID),
	usuarioSetReputacion(UsuarioActivo, -1, NuevoUsuarioActivo),
	modificarUsuarioPorNombre(Usuarios, AutorVoto, NuevoUsuarioActivo, NuevosUsuarios),
	usuarioPorNombre(NuevosUsuarios, AutorRespuesta, UsuarioAutorRespuesta),
	usuarioSetReputacion(UsuarioAutorRespuesta, -2, NuevoUsuarioAutorRespuesta),
	modificarUsuarioPorNombre(NuevosUsuarios, AutorRespuesta, NuevoUsuarioAutorRespuesta, NuevosUsuariosFinal),
	respuestaSetVotos(Respuesta, -1, NuevaRespuesta),
	modificarRespuestaPorID(Respuestas, ID, NuevaRespuesta, NuevasRespuestas), !.





/*
Ejemplos de llamada

stackRegister:
	stack(StackInicial, 2), stackRegister(StackInicial, "Christofer", "123456", StackResultante).
	stack(StackInicial, 1), stackRegister(StackInicial, "Christofer", "123456", StackResultante).
	stack(StackInicial, 2), stackRegister(StackInicial, "Christofer", "123456", StackResultante), stackRegister(StackResultante, "Javier", "Javi123", NuevoStackResultante).

stackLogin:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "jaime", "456", StackResultante).
	stack(StackInicial, 2), stackRegister(StackInicial, "Christofer", "123456", StackResultante), stackLogin(StackResultante, "Christofer", "123456", NuevoStackResultante).

ask:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), ask(StackResultante, "06-12-2020", "Como pregunto?", ["ayuda"], NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), ask(StackResultante, "06-12-2020", "Como respondo una pregunta ?", ["ayuda"], NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "jaime", "456", StackResultante), ask(StackResultante, "06-12-2020", "Como termino el lab ?", ["ayuda", "urgente"], NuevoStackResultante).

answer:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), answer(StackResultante, "06-12-2020", 2, "Asi se responde ?", ["ayuda"], NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), answer(StackResultante, "06-12-2020", 3, "No te puedo ayudar", ["Perdon"], NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), answer(StackResultante, "06-12-2020", 4, "Solo tienes que descargar un interpretador", ["Prolog"], NuevoStackResultante).

accept:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), accept(StackResultante, 1, 1, NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "jaime", "456", StackResultante), accept(StackResultante, 2, 2, NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "jose", "jose654", StackResultante), accept(StackResultante, 4, 5, NuevoStackResultante).

stackTostring:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), stackTostring(StackResultante, StackStr).
	stack(StackInicial, 1), stackTostring(StackInicial, StackStr).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), ask(StackResultante, "06-12-2020", "Como pregunto?", ["ayuda"], NuevoStackResultante), stackTostring(NuevoStackResultante, StackStr).

vote:
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), getQuestion(StackResultante, 1, Pregunta), vote(StackResultante, Pregunta, true, NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), getQuestion(StackResultante, 1, Pregunta), vote(StackResultante, Pregunta, false, NuevoStackResultante).
	stack(StackInicial, 1), stackLogin(StackInicial, "chris", "chris123", StackResultante), getAnswer(StackResultante, 2, 2, Pregunta), vote(StackResultante, Pregunta, true, NuevoStackResultante).
*/