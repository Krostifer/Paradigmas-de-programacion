#lang racket

#|
TDA RacketFlix
    Lista que de TDAs
         Peliculas(lista de TDAs Pelicula)
         Usuarios(lista de TDAs Usuario)
    Ejemplo: '((Pelicula1) (Pelicula2)) ((Usuario1) (Usuario2)))

TDA Usuarios
   Lista de TDAs Usuario
Ejemplo: '((Usuario1) (Usuario2) ...)

TDA Usuario
    Lista que contiene los siguiente
         username(string)
         pass("string")
         Cartera (entero)
         Preferencias(lista de strings)
         peliculasAdquiridas (lista de TDAs Pelicula)
         Rut(string)
    Ejemplo: '("Pepe" "1234" 666 ("romance" "drama") (Pelicula1 Pelicula2) "182345438")

TDA Peliculas
   Lista de TDAs Peliculas
Ejemplo: '((Pelicula1) (Pelicula2))

TDA Pelicula
    Lista que contiene los siguentes elementos
         id(entero)
         nombre (string)
         duracion (entero)
         anio(entero)
         tags(lista de strings)
         costo(entero)
         
    Ejemplo: '(1 "Forrest Gump" 142 1994 ("romance" "drama") 1700)
|#

;***************** RACKETFLIX *********************

;**** CONSTRUCTOR ****

;Crea el TDA RacketFlix
;No recibe argumentos
;Retorna el TDA RacketFlix

(define (construirRacket)
  (list (list ) (list ))
  )

;***************** USUARIOS *********************
(define getUsuarios (lambda (Racket)
                     (car Racket)
                       )
  )

;***************** USUARIO *********************

;**** CONSTRUCTOR ****

;Crea un usuario
;Recibe como argumentos el nombre de usuario(string) y su contraseña (string)
;Retorna un TDA usuario

(define crearUsuario(lambda (user pass)
                      (list user pass 0 '() "")
                     )
  )

;*********************************************************************

;Registra a un nuevo usuario
;Recibe como argumentos el nombre de usuario(string), la contraseña(string), RacketFlix (lista de TDAs)
;Retorna RacketFlix con el usuario ya registrado``
(define registrar (lambda (user pass Racket)
                    (if (null? (getUsuarios Racket))
                        (list (crearUsuario user pass) (cdr Racket))
                        (list (cons (getUsuarios) (crearUsuario user pass)) (cdr Racket))
                     )
                   )
  )

;Permite que un usuario compre una pelicula
;Recibe como argumentos el nombre de usuario(string) que quiere comprar, 