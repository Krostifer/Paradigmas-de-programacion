#lang racket
(define peliculas '((1 "Forrest Gump" 1700 142 1994 ("romance" "drama")) (2 "Si" 1700 142 1994 ("eso" "tambien"))))
#|
A)

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
         costo(entero)
         duracion (entero)
         anio(entero)
         tags(lista de strings)
         
         
    Ejemplo: '(1 "Forrest Gump" 1700 142 1994 ("romance" "drama"))
|#

;***************** USUARIOS *********************

;**** CONSTRUCTOR ****

;Crea un TDA usuarios
;No recibe argumentos
;Retorna un TDA usuarios

(define (crearUsuarios)
             (list )
  )

;***************** USUARIO *********************

;**** CONSTRUCTOR ****

;Crea un usuario
;Recibe como argumentos el nombre de usuario(string), contraseña (string), cartera(entero), prefetencias(list), peliculas(list) y rut(string)
;Retorna un TDA usuario

(define crearUsuario (lambda (user pass cartera preferencias peliculas rut)
                      (list user pass cartera preferencias peliculas rut)
                     )
  )

;**** Getter ****

;Entrega un usuario de una lista de usarios
;Recibe como argumentos un TDA Usuarios (lista de usuario)
;Retorna un usuario

(define getUsuario (lambda (lista)
                     (car lista)
                    )
  )

;Entrega el nombre que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna el nombre que tiene un usuario

(define getNombre (lambda (usuario)
                     (car usuario)
                    )
  )

;Entrega la contraseña que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna la contraseña que tiene un usuario

(define getPass (lambda (usuario)
                     (caar usuario)
                    )
  )



;Entrega el dinero que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna el nombre que tiene un usuario

(define getDinero (lambda (usuario)
                     (caaar usuario)
                    )
  )

;Entrega las preferencias que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna las preferencias que tiene un usuario

(define getPreferencias (lambda (usuario)
                     (car(cdddr usuario))
                    )
  )
;Entrega las peliculas adquiridas que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna las pelicualas adquiridas que tiene un usuario

(define getPeliculasAdquiridas (lambda (usuario)
                     (cadr(cdddr usuario))
                    )
  )
;Entrega el rut que tiene un usuario
;Recibe como argumentos un TDA Usuario
;Retorna el rut que tiene un usuario

(define getRut (lambda (usuario)
                     (caddr(cdddr usuario))
                    )
  )


;***************** PELICULAS *********************

;**** CONSTRUCTOR ****

;Crea un TDA peliculas
;No recibe argumentos
;Retorna un TDA peliculas

;**** Getter ****

;Entrega una pelicula de una lista de pelicula
;Recibe como argumentos un TDA Peliculas
;Retorna la pelicula de una lista de pelicula

(define getPelicula (lambda (peliculas)
                     (car peliculas)
                    )
  )

;***************** PELICULA *********************

;**** CONSTRUCTOR ****

;Crea un TDA Pelicula
;Recibe como argumentos el id(entero), nombre (string), duracion (entero), anio(entero), tags(lista de strings) y costo(entero)
;Retorna un TDA pelicula

(define crearPelicula(lambda (id nombre costo duracion anio tags)
                      (list id nombre duracion anio tags costo)
                     )
  )

;**** Getter ****

;Entrega el precio de una pelicula
;Recibe como argumentos un TDA Pelicula
;Retorna el precio de una pelicula

(define getPrecio (lambda (pelicula)
                     (caaar (cdddr pelicula))
                    )
  )

;Entrega el tiempo que dura una pelicula
;Recibe como argumentos un TDA Pelicula
;Retorna el tiempo que dura una pelicula

(define getDuracion (lambda (pelicula)
                     (car (cdddr pelicula))
                    )
  )
;*********************************************************************

;B)
;Registra a un nuevo usuario
;Recibe como argumentos el nombre de usuario(string), la contraseña(string), RacketFlix (lista de TDAs)
;Retorna la lista de usuarios con el usuario ya registrado
(define registrar (lambda (user pass usuarios)
                    (if (null? usuarios)
                        (list (crearUsuario user pass))
                        (cons (getUsuario) (crearUsuario user pass))
                     )
                   )
  )

;C)
;Permite que un usuario compre una pelicula
;Recibe como argumentos un usuario y la pelicula a comprar
;En caso de que se pueda comprar retorna al usuario con la pelicula ya comprada  y false en caso de que no se pueda comprar
(define comprar (lambda (pelicula usuario)
                  (if (< (getDinero usuario) (getPrecio pelicula))
                      #f
                      (crearUsuario (getNombre) (getPass) (- (getDinero usuario) (getPrecio pelicula)) (getPreferencias) (cons (getPeliculasAdquiridas) pelicula) (getRut))
                   )
                 )
  )

;D)
;Permite filtrar peliculas de acuerdo a un cierto criterio
;Recibe como argumento la lista de peliculas y una función para filtrar
;Retorna una nueva lista con los elementos que cumplen el criterio
(define filtrarPeliculas (lambda (listaPeliculas criterio)
                             (if (null? listaPeliculas)
                                 '()
                                 (if (criterio (car listaPeliculas))
                                     (cons (car listaPeliculas) (filtrarPeliculas (cdr listaPeliculas) criterio))
                                     (filtrarPeliculas (cdr listaPeliculas) criterio)
                                     )
                                 )
                           )
)


;E)
;i)
;Filtra las peliculas que se encuentren dentro de un rago de precio
;Recibe como argumentos la lista de peliculas, el precio minimo y el precio maximo
;Retorna una nueva lista con los elementos que cumplen con el precio
(define rangoDuracion (lambda (listaPeliculas min max)
                             (if (null? listaPeliculas)
                                 '()
                                 (if (and (< (getDuracion (car listaPeliculas)) max) (> (getDuracion (car listaPeliculas)) min))
                                     (cons (car listaPeliculas) (rangoDuracion (cdr listaPeliculas) min max))
                                     (rangoDuracion (cdr listaPeliculas) min max)
                                     )
                                     )
                                 )
  )

  

;F)

;Implementación de la funcion map, aplica la función entregada a las peliculas de una lista de peliculas
;Recibe como argumento una funcion y una lista de peliculas
;Retorna una nueva lista de peliculas con cada pelicula modificada
;Esta función es del tipo de recursion natural
(define mapPeliculas (lambda (funcion listaPeliculas)
                ;caso base
                (if (null? listaPeliculas) ;Condición de borde, indica que la lista es nula o que se llego al final del la lista
                    null
                    (cons (funcion (car listaPeliculas)) (mapPeliculas funcion (cdr listaPeliculas))) ;Descomposición recursiva para aplicar función a cada elemento de la cola
                )
              )
)


;Aplica una rebaja a las peliculas que cumplan un cierto criterio
;Recibe como argumentos la lista de peliculas, la funcion de rebaja y el criterio
;Retorna una nueva lista de pelicualas con los precios actualizados


(define rebaja (lambda (listaPeliculas funcion criterio)
                             (if (null? listaPeliculas)
                                 '()
                                 (if (criterio (car listaPeliculas))
                                     (cons (mapPeliculas (car listaPeliculas)) (filtrarPeliculas (cdr listaPeliculas) criterio))
                                     (filtrarPeliculas (cdr listaPeliculas) criterio)
                                     )
                                 )
                           )
)``