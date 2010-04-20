(define (delete_element_at list counter)
  (cond
    [(zero? counter) (cdr list)]
    [else (cons (car list) (delete_element_at (cdr list) (- counter 1)))])
  )

(define (equal_list? list1 list2)
   (cond
     [(not (eq? (length list1) (length list2))) #f]
     [(null? list1) #t]
     [(eq? (car list1) (car list2)) (equal_list? (cdr list1) (cdr list2))]
     [else #f]
     )
  )

(define (subset? seqs_to_search seq_to_find)
  (cond
    [(null? seqs_to_search) #f]
    [(equal_list? (car seqs_to_search) seq_to_find) #t]
    [else (subset? (cdr seqs_to_search) seq_to_find)])
  )

(begin 
  (subset? '((1 2) (1 3) (1 4)) '(1 2))
  )