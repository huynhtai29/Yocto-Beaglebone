OUTPUT = build
DIR = $(abspath ./)
CC = gcc

INC = $(addprefix ./,inc)
SRC = $(addprefix ./,src)
S_SRC = $(wildcard $(SRC)/*.c)
S_INC = $(wildcard $(INC)/*.h)
OBJS = $(S_SRC:.c=.o)

LIB = libbasic.so.1.0.0
DIR_LIB=

all: lib hello
%.o : %.c
	$(CC) -c -o $@ $^ -I$(INC)  
lib:$(OBJS)
	$(CC) -shared -o $(LIB) $(OBJS)
hello:
	$(CC) -o $@ main.c $(DIR_LIB) -I$(INC)
clean:
	rm $(OBJS)
	rm -r $(LIB)

