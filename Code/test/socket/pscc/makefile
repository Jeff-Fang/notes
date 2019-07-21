CC=clang
IDIR=.
CFLAGS=-I$(IDIR)

BDIR=bin

socket_test: client_tcp.c server_tcp.c
	$(CC) -o $(BDIR)/client_tcp client_tcp.c
	$(CC) -o $(BDIR)/server_tcp server_tcp.c

	
client: client_tcp.c
	$(CC) -o $(BDIR)/client_tcp client_tcp.c

server: server_tcp.c
	$(CC) -o $(BDIR)/server_tcp server_tcp.c

.PHONY: clean rmbin
clean:
	rm *.o

rmbin:
	rm bin/*


