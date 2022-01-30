#lang racket
(define mapa1 '((24 2 2 "rojo") (24 1 2 "rojo") (24 3 2 "blanco") (24 3 3 "rojo")))
#|
TDA mapa
   *****ASUMO QUE PARA LA CREACION DE ESTE TDA YA EXISTE UNA LISTA CON LOS DATOS DE CADA ESTACION**** 
   Lista que almacena los TDAs estación, esta almacenará solo donde existan estaciones
Ejemplo: '((Estacion1) (Estacion2) (Estacion3)...)

TDA estacion
   Contiene los datos registrados por cada las estaciones de monitoreo
      Temperatura (entero) temepatura medida por la estacion
      I (entero)  Representa el valor de la fila donde se encuentra la estacion
      J (entero)  Representa el valor de la columna donde se encuentra la estacion
      Color (string) Color de la estacion en el mapa
Ejemplo: '(25 1 1 "rojo")

|#


;**********************************************************************************

;***************** MAPA *********************
;**** Constructor ****

;Asumiendo de que partida existe una lista con las estaciones del mapa (imagen), crea el TDA mapa con las estaciones existentes
;Recibe como argumento la lista inicial de estaciones (lista de listas)
;
(define crearMapa (lambda (listaEstaciones)
                             (if (null? listaEstaciones)
                                 '()
                                 (if (equal? "blanco" (getColor(car listaEstaciones)))
                                     (crearMapa (cdr listaEstaciones))
                                     (cons (car listaEstaciones) (crearMapa (cdr listaEstaciones)))
                                     )
                                     )
                                 )
  )


;***************** ESTACION *********************
;**** GETTER ****

;Entrega la temperatura de la estacion
;Recibe como argumentos un TDA estacion (lista)
;Retorna la temperatura (entero) de la estacion

(define getTemperatura (lambda (estacion)
                         (car estacion)
                        )
  )

;Entrega el color de la estacion
;Recibe como argumentos un TDA estacion (lista)
;Retorna el color (String) de la estacion

(define getColor (lambda (estacion)
                         (car(cdr(cdr(cdr estacion))))
                        )
  )

;Entrega la posicion en I de la estacion
;Recibe como argumentos un TDA estacion (lista)
;Retorna la posicion en I (entero) de la estacion

(define getI (lambda (estacion)
                         (car(cdr estacion))
                        )
  )

;Entrega la posicion en J de la estacion
;Recibe como argumentos un TDA estacion (lista)
;Retorna la posicion en J (entero) de la estacion

(define getJ (lambda (estacion)
                         (car(cdr(cdr estacion)))
                        )
  )



;**********************************************************************************


;Permite obtener aquellas estaciones que cumplen un creterio C
;Recibe como argumentos un TDA mapa (lista de estaciones) y el criterio C
;Retorna una nueva lista con los elementos que cumplen el criterio

(define filtrarEstaciones (lambda (mapa criterio)
                             (if (null? mapa)
                                 '()
                                 (if (criterio (getTemperatura (car mapa)))
                                     (cons (car mapa) (filtrarEstaciones (cdr mapa) criterio))
                                     (filtrarEstaciones (cdr mapa) criterio)
                                     )
                                 )
                           )
)

``

;Permite calcular la temperatura de una vecinidad
;Recibe como argumentos un mapa (lista de listas), una estacion centro de la vecindad (lista) y una funcion estadistica
;Retorna la temperatura estimada de la vecindad (entero)

(define estimar (lambda (lista estacion funcion)
                  (funcion (vecinidad lista estacion))
                    )
  )








