%practical 3.1
%find the maximum of three numbers
maxof(A,B,C,Z):-A>B,A>C,Z is A.
maxof(A,B,C,Z):-A>B,A<C, Z is C.
maxof(A,B,C,Z):-B>A,B>C, Z is B.
maxof(A,B,C,Z):-B>A,B<C, Z is C.
maxof(A,B,C,Z):-C>A,C>B, Z is C.
maxof(A,B,C,Z):-C>A,C<B, Z is B.

%practical 3.2
%find the minimum of three numbers
minof(A,B,C,Z):-A<B,A<C,Z is A.
minof(A,B,C,Z):-A<B,A>C,Z is C.
minof(A,B,C,Z):-B<A,C>B,Z is B.
minof(A,B,C,Z) :-B<A,C<B,Z is C.
minof(A,B,C,Z):- C<A,B<C, Z is B.
minof(A,B,C,Z):- C>A,B>C, Z is A.

%practical 3.3
%write a program to find the given number is odd or even.
even(Z):- Z = "Even".
odd(Z):- Z = "Odd".
checkoddeven(X,Z):- 0 =:= mod(X,2),even(Z).
checkoddeven(X,Z):- 0 =\= mod(X,2),odd(Z).

%practical 3.4
%write a program to check the number is positive or negetive
pos(Z):- Z = "Positive".
neg(Z):- Z = "Negative".
checkposneg(X,Z):- X>0,pos(Z).
checkposneg(X,Z):- X<0,neg(Z).


%practical 3.5
% write a program to find out the factorial of the given number
factorial(N,Z):- N < 2, Z = 1.
factorial(N,Z):- M is N-1,factorial(M,T),Z is N*T.

%practical 3.6
%Print Fibonacci series upto given number
fib(0,Z):- Z is 0.
fib(1,Z):- Z is 1.
fib(A,Z):-A>1,W is A-1,Y is A-2,fib(W,T),fib(Y,S),Z is T+S.
fibonaci(0):- print(0).
fibonaci(1):- print(1).
fibonaci(A):-A > 1,fib(A,Z),A1 is A-1,fibonaci(A1),write(' '),print(Z).
