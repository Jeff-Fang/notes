/* --- client.c --- */
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>

int socket_connect(int argc, char const *argv[])
{
  int sockfd = 0, n = 0;
  char recvBuff[1024];
  struct sockaddr_in serv_addr;
  int sock = 0;
  char *hello = "Hello from client";

  if(argc != 2)
  {
    printf("\n Usage: %s <ip of server> \n",argv[0]);
    return 1;
  }

  memset(recvBuff, '0',sizeof(recvBuff));

  /* a socket is created through call to socket() function */
  if((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
  {
    printf("\n Error : Could not create socket \n");
    return 1;
  }

  memset(&serv_addr, '0', sizeof(serv_addr));

  serv_addr.sin_family = AF_INET;
  serv_addr.sin_port = htons(50007);

  if(inet_pton(AF_INET, argv[1], &serv_addr.sin_addr)<=0)
  {
    printf("\n inet_pton error occured\n");
    return 1;
  }

  /* Information like IP address of the remote host and its port is
   * bundled up in a structure and a call to function connect() is made
   * which tries to connect this socket with the socket (IP address and port)
   * of the remote host
   */
  if( connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
  {
    printf("\n Error : Connect Failed \n");
    return 1;
  }

  send(sock , hello , strlen(hello) , 0 );
  printf("Hello message sent\n");

  /* Once the sockets are connected, the server sends the data (date+time)
   * on clients socket through clients socket descriptor and client can read it
   * through normal read call on the its socket descriptor.
   */
  while ( (n = read(sockfd, recvBuff, sizeof(recvBuff)-1)) > 0)
  {
    recvBuff[n] = 0;
    if(fputs(recvBuff, stdout) == EOF)
    {
      printf("\n Error : Fputs error\n");
    }
  }

  if(n < 0)
  {
    printf("\n Read error \n");
  }

  return 0;
}

int main(int argc, char const *argv[])
{
  while(1) {
    socket_connect(argc, argv);
    sleep(1);
  }
  return 0;
}
