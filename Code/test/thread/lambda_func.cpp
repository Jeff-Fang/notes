#include <iostream>
#include <thread>

int main()
{
  int x = 9;
  std::thread threadObj([]{
    for (int i = 0; i < 10000; i++)
      std::cout << "Display Thread Executing" << std::endl;
  });

  for (int i = 0; i < 10000; i++)
    std::cout << "Display From Main Thread" << std::endl;

  std::cout << "Waiting For Thread to complete" << std::endl;
  threadObj.join();
  std::cout << "Exiting from Main Thread" << std::endl;
  return 0;
}
