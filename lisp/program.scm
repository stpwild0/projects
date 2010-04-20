(define (delete_element_at set counter)
  (cond
    [(zero? counter) (cdr set)]
    [else (cons (car set) (delete_element_at (cdr set) (- counter 1)))])
  )

(define (contain? )
  )

(begin 
  (contain? '((1 2) (1 3) (1 4)) '(1 2))
  )