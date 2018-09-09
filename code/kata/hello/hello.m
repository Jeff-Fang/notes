// hello.m
// An Objective-C HelloWorld Programme.
// Use follwing commands to compile and run:
//  $ gcc -framework Foundation hello.m -o hello_objc

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[]) {
  @autoreleasepool {
    NSLog(@"Programming is fun\n Programming in Objective-C is even more fun!");
  }
  return 0;
}
