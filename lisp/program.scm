(define (last list)
  (cond
    [(null? (cdr list)) (car list)]
    [else (last (cdr list))])
  )

(define (delete_element_at list pos)
  (cond
    [(zero? pos) (cdr list)]
    [else (cons (car list) (delete_element_at (cdr list) (- pos 1)))])
  )

(define (equal_list? list1 list2)
   (cond
     [(not (eq? (length list1) (length list2))) #f]
     [(null? list1) #t]
     [(eq? (car list1) (car list2)) (equal_list? (cdr list1) (cdr list2))]
     [else #f]
     )
  )

(define (subset? seq_to_find seqs_to_search)
  (cond
    [(null? seqs_to_search) #f]
    [(equal_list? (car seqs_to_search) seq_to_find) #t]
    [else (subset? seq_to_find (cdr seqs_to_search))])
  )

(define (generate_subseqs seq pos)
  (cond 
    [(zero? pos)
     (list (delete_element_at seq pos))]
    [else (cons (delete_element_at seq pos)
                (generate_subseqs seq (- pos 1)))])
  )

(define (subseqs seq)
  (generate_subseqs seq (- (length seq) 1))
  )

(define (prepend_no_dup to_prepend original)
  (cond
    [(subset? (car to_prepend) original)
     (cond
       [(null? (cdr to_prepend)) original]
       [else (prepend_no_dup (cdr to_prepend) original)])]
    [else 
     (cond
       [(null? (cdr to_prepend)) (cons (car to_prepend) original)]
       [else (cons (car to_prepend) (prepend_no_dup (cdr to_prepend) original))
             ])]
    )
  )

(begin 
  (prepend_no_dup (subseqs '(1 3 4)) (subseqs '(2 3 4)))
  )