(define (initial_array size prev_array)
  (if (positive? size)
      (initial_array (- size 1) (cons (cons size '()) prev_array))
      prev_array)
  )

(define (expand size sorted_array)
  (if (null? sorted_array)
      (expand size (initial_array size '()))
      sorted_array
  ))

(begin
  (expand 4 '())
  )