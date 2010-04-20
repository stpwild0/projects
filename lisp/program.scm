(define (last list)
  (if (null? (cdr list))
      (car list)
      (last (cdr list)))
  )

(define (expand_single set previous)
  (if (null? previous)
      (expand_single )
      ())
  )


(define (next_iter size list previous)
  (if (null? previous) 
      (next_iter (cdr previous) (expand_single (car previous) '()))
      ())
  )

(define (initial_array size prev_array)
  (if (positive? size)
      (initial_array (- size 1) (cons (cons size '()) prev_array))
      (list (list empty) prev_array))
  )

(define (build size sorted_array)
  (next_iter size (last sorted_array) '())
  )

(begin
  (build 4 (initial_array 4 '()))
  )