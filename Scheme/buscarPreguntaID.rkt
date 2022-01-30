#lang racket
;Funcion que buca una pregunta por su ID
;Recibe como argumentos una lista de preguntas(lista) y un ID a buscar (entero)
;Retorna una pregunta(lista)
;Esta funcion utiliza la recursion de cola
(define (buscarPreguntaID lista ID)
  (if (null? lista)
      '()
      (if (= (pregunta->getID (listaPreguntas->getPregunta lista)) ID)
          (listaPreguntas->getPregunta lista)
          (buscarPreguntaID (cdr lista) ID)
       )
   )
  )