
member(X,[X|_]).
member(X,[_|T]) :- member(X,T).

add(A,T,[A|T]).

del(X, [X], []).
del(X,[X|L1], L1).
del(X, [Y|L2], [Y|L1]) :- delete(X,L2,L1).

list_length([],0).
list_length([_|T],N) :- length(T,N1), N is N1 + 1.

concat([],L,L).
concat([X1|L1],L2,[X1|L3]) :- concat(L1,L2,L3).
