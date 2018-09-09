#include <stdio.h>

int main(void){
  int A[3] = {[0 ... 2] = -1};
  for(int i = 0; i < 3; ++i){
    printf("%d\n", A[i]);
  }
  return 0;
}
