%basic family facts

parent(elizabeth,charles).
parent(philip,charles).
parent(elizabeth,anne).
parent(philip,anne).
parent(elizabeth,andrew).
parent(philip,andrew).
parent(diana,william).
parent(charles,william).
parent(diana,harry).
parent(charles,harry).
parent(diana,amy).
parent(charles,amy).
parent(anne, helly).
parent(vernon,helly).
male(philip).
male(charles).
male(andrew).
female(anne).
female(elizabeth).
female(diana).
female(amy).

%rules for the above facts
%practical 2.1
%basic relationship rules for the family
child(X,Y) :- parent(Y,X).
mother(X,Y) :- female(X),parent(X,Y).
father(X,Y) :- male(X),parent(X,Y).
son(X,Y) :- male(X),parent(Y,X).
daughter(X,Y) :- female(X),parent(Y,X).
siblings(X,Y):- parent(Z,X),parent(Z,Y).


%practical 2.2
%extra relations
uncle(X,Y) :- male(X),siblings(X,Z),parent(Z,Y).
aunt(X,Y) :- female(X),siblings(X,Z),parent(Z,Y).
nephew(X,Y) :- male(X),uncle(Y,X);aunt(Y,X).
niece(X,Y) :- female(X),uncle(Y,X);aunt(Y,X).
grandfather(X,Y) :- father(X,Z),father(Z,Y).
grandmother(X,Y) :- mother(X,Z), father(Z,Y).
grandchild(X,Y) :- grandfather(Y,X);grandmother(Y,X).
cousin(X,Y):- parent(W,X), parent(Z,Y),siblings(W,Z).

%practical 2.3
% recursive rules
ancestor(X,Y) :- parent(X,Y).
ancestor(X,Y) :- parent(X,Z),ancestor(Z,Y).

descendent(X,Y) :- child(X,Y).
descendent(X,Y) :- parent(Z,X),descendent(Z,Y).


%practical 2.4
%edges practical
edge(a,b).
edge(b,c).
edge(b,d).
edge(b,e).
edge(c,e).
edge(d,e).
edge(e,f).
edge(g,d).

%recursive rules
connected(X,Y):-edge(X,Y). 
connected(X,Z):-edge(X,Y),connected(Y,Z).
