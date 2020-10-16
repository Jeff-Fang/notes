#include <stdio.h>  /* printf     */
#include <getopt.h> /* getopt API */
#include <iostream>

int main(int argc, char *argv[])
{
  int opt;

  while ((opt = getopt(argc, argv, "abX")) != -1) 
  {
     switch (opt) 
     {
      case 'a':
        std::cout << "Option a was provided" << std::endl;
        break;
      case 'b':
        std::cout << "Option b was provided" << std::endl;
        break;
      case 'X':
        std::cout << "Option X was provided" << std::endl;
        break;
     }
  }

  std::cout << "No option provided" << std::endl;
  
  return 0;
}
