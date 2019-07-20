#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>

int main() {

  int sockfd, connfd;

  // specify an address for the socket
  struct sockaddr_in server_address, client_address;
  server_address.sin_family = AF_INET;
  server_address.sin_port = htons(10102);
  //server_address.sin_addr.s_addr = htonl(INADDR_ANY);
  server_address.sin_addr.s_addr = INADDR_ANY;

  sockfd = socket(AF_INET, SOCK_STREAM, 0);
  // bind the socket to our specified IP and port
  if (bind(sockfd, (struct sockaddr*) &server_address, sizeof(server_address)) != 0) {
    printf("socket bind failed...\n");
    return -1;
  } else printf("socket successfully binded...\n");

  if ((listen(sockfd, 5)) != 0) {
    printf("Listen failed...\n");
    return -1;
  } else printf("server listening..\n");

  int len = sizeof(client_address);
  connfd = accept(sockfd, (struct sockaddr*) &client_address, (socklen_t *) &len);
  if (connfd < 0) {
    printf("server acccept failed...\n");
    return -1;
  } else printf("server acccepted the client...\n");

  // send the message
  char server_message[256] = "You have reached the server!";
  send(connfd, server_message, sizeof(server_message), 0);

  // get msg from client
  char client_msg[25];
  bzero(client_msg, sizeof(client_msg));
  // if (recv(connfd, &client_msg, sizeof(client_msg), 0) != 0) {
  if (read(connfd, &client_msg, sizeof(client_msg)) != 0) {
    printf("Error while receiving data.\n");
    return -1;
  } else printf("Data received. \n");
  //int n = read(sockfd, client_msg, sizeof(client_msg));
  //if (n<0) printf("Error %d while reading data./n", n);
  // print client msg
  printf("The client sent: %s\n", client_msg);





   // close the socket
  close(sockfd);


  return 0;
}

