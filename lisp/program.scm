; Kevin Lin 9051-08229 Apr 22, 2010

(define (size list)
  (cond
    [(null? list) 0]
    [else (+ 1 (size (cdr list)))])
  )

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

(define (delete_last list)
  (delete_element_at list (- (size list) 1))
 )

(define (equal_list? list1 list2)
   (cond
     [(not (eq? (size list1) (size list2))) #f]
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
     (cons (delete_element_at seq pos) '())]
    [else (cons (delete_element_at seq pos)
                (generate_subseqs seq (- pos 1)))])
  )

(define (subseqs seq)
  (generate_subseqs seq (- (size seq) 1))
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

(define (power_rec remaining ans)
  (cond
    [(null? (car remaining)) (cons '() ans)]
    [else (power_rec 
           (prepend_no_dup (subseqs (last remaining)) (delete_last remaining))
           (prepend_no_dup (subseqs (last remaining)) ans)
           )]
    )
  )

(define (POWER seq)
  (cdr (power_rec (cons seq '()) (cons seq '()))))

(newline)
(POWER '())
(POWER '(1))
(POWER '(1 2))
(POWER '(1 2 3))
(POWER '(1 2 3 4))
