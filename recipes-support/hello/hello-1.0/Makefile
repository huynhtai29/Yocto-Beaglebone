CC=gcc
RM=rm

CFLAGS= -Wall
LDFLAGS=

INC=
LIB=libbasic

DESTDIR=
BINDIR=/usr/bin

SOURCES=hello.c
OBJECTS=$(SOURCES:.c=.o)

EXECUTABLE=hello

all: $(SOURCES) $(EXECUTABLE)

%.o : %.c
	$(CC) -c -o $@ $^ $(CFLAGS)
                          
$(EXECUTABLE): $(OBJECTS)
	$(CC) $(LDFLAGS) $^ -o $@ -I$(INC) $(LIB)

clean:
	$(RM) $(EXECUTABLE) *.o

install: $(EXECUTABLE)
	mkdir -p $(DESTDIR)/$(BINDIR)
	install -m 0755 $< $(DESTDIR)/$(BINDIR)
