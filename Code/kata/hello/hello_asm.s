# File name       : hello_asm.s
# Created date    : 11/12/2015
# Last update     : 11/12/2015
# Description     : A 'Hello World' assembly app for Mac OS X, using as to compile.
# Build using these commands:
#  $ as hello_asm.s -o hello_asm.o
#  $ ld hello_asm.o -e _main -o hello_asm

.section __DATA,__data
str:
    .asciz "Hello Assembly!\n"

.section __TEXT,__text
.globl _main
_main:
    movl $0x2000004, %eax
    movl $1, %edi
    movq str@GOTPCREL(%rip), %rsi
    movq $100, %rdx
    syscall

    movl $0, %edx
    movl $0x2000001, %eax
    syscall
