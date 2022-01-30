#lang racket

(define L1 (list (list "casa" 1) (list "cama" 2) (list "zapato" 3)))

(define (compras)
    (list )
 )

(define crearArticulo (lambda (nombre precio) 
      (list nombre precio)
   )
)

(define cambiarPrecio(lambda (lista elemento nuevoPrecio)
                                (buscarPrecio lista elemento nuevoPrecio (compras))
                            )
)

(define buscarPrecio (lambda (lista elemento nuevoPrecio listaF)
                 (if (null? lista)
                    (lista)
                    (if (equal? elemento (car(car lista)))
                       (append (append listaF (list (crearArticulo elemento nuevoPrecio))) (cdr lista))
                       (buscarPrecio (cdr lista) elemento nuevoPrecio (append listaF (list (car lista))))
                     )
                  )
                 )
  )