(define (last list)
  (if (null? (cdr list))
      (car list)
      (last (cdr list)))
  )

(define (multiply2 set)
  list
  
  )

(define (multiply size list)
  (if (<= (length (cdr list)) (length (last list)))
      (cons (cdr list) (multiply (car list)))
      list
   )
  )

(define (initial_array size prev_array)
  (if (positive? size)
      (initial_array (- size 1) (cons (cons size '()) prev_array))
      (list (list empty) prev_array))
  )

(define (expand size sorted_array)
  (multiply size (last sorted_array))
  )

(begin
  (expand 4 (initial_array 4 '()))
  )