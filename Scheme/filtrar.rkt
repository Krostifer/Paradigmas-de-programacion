#lang racket

(define L1 (list (list "casa" 1) (list "cama" 2) (list "zapato" 3)))

(define (compras)
    (list )
 )

(define crearArticulo (lambda (nombre precio) 
      (list nombre precio)
   )
)

(define precioMenor (lambda (lista precioLimite)
                                (filtrarPrecio lista precioLimite (compras))
                            )
)

(define filtrarPrecio (lambda (lista precioLimite listaF)
                 (if (null? lista)
                    listaF
                    (if (< (car(cdr(car lista))) precioLimite)
                       (filtrarPrecio (cdr lista) precioLimite (cons (caar lista) listaF))
                       (filtrarPrecio (cdr lista) precioLimite listaF)
                     )
                  )
                 )
  )