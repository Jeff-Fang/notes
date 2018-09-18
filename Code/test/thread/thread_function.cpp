#include <iostream>
#include <thread>

void thread_function() {
  for(int i = 0; i < 10000; i++)
    std::cout << "thread function Executing" << std::endl;
}

int main() {
  std::thread threadObj(thread_function);
  for(int i = 0; i < 10000; i++)
    std::cout << "Display From MainThread" << std::endl;
  return 0;
}
