(define (last list)
  (if (null? (cdr list))
      (car list)
      (last list))
  )

(define (initial_array size prev_array)
  (if (positive? size)
      (initial_array (- size 1) (cons (cons size '()) prev_array))
      (list (list empty) prev_array))
  )

(define (expand size sorted_array)
  (if (null? sorted_array)
      (expand size (initial_array size '()))
      sorted_array
  ))

(begin
  (expand 4 (initial_array 4 '()))
  )