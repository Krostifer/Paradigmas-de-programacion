#lang racket
(define L1 (list 1 2 3 "si"))

(define (compras)
    (list )
 )

(define eliminar (lambda (lista elemento)
                                (buscar lista elemento (compras))
                            )
)

(define buscar (lambda (lista elemento listaF)
                 (if (null? lista)
                    (lista)
                    (if (equal? elemento (car lista))
                       (append listaF (cdr lista))
                       (buscar (cdr lista) elemento (append listaF (list (car lista))))
                     )
                  )
                 )
  )