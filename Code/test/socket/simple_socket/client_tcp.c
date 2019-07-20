#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <sys/types.h>
#include <sys/socket.h>

#include <netinet/in.h>
#include <arpa/inet.h>

int main() {
  int sockfd;
  sockfd = socket(AF_INET, SOCK_STREAM, 0);

  // specify an address for the socket
  struct sockaddr_in server_address;
  server_address.sin_family = AF_INET;
  server_address.sin_port = htons(10102);
  server_address.sin_addr.s_addr = inet_addr("127.0.0.1");

  // connect the socket
  if (connect(sockfd, (struct sockaddr *) &server_address, sizeof(server_address)) != 0) {
    printf("There was an error while making a connection to the remote socket \n\n");
    return -1;
  } else printf("Client connecting...\n");

  char msg1[25];
  bzero(msg1, sizeof(msg1));
  strcpy(msg1, "111111111111111");
  //char msg2[25] = "222222222222222";
  //char msg3[25] = "333333333333333";
  //char msg4[25] = "444444444444444";
  //char msg5[25] = "555555555555555";

  write(sockfd, msg1, sizeof(msg1));
  // send(sockfd, msg1, sizeof(msg1), 0);
  //int n=0;
  //n = write(sockfd, msg1, sizeof(msg1));
  //if (n<0) printf("Error %d while writing data.\n", n);

  // receivce data from the server
  char server_response[256];
  recv(sockfd, &server_response, sizeof(server_response), 0);

  // print out the server's response
  printf("The server sent the data: %s\n", server_response);


  // close the socket
  close(sockfd);


  return 0;
}

