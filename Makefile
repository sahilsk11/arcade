all: convert

convert: slapjack.c
	gcc -g -o slapjack slapjack.c
	rm -rf slapjack.dYSM
