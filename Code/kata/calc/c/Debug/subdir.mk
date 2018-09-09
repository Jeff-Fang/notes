################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../evalPost.c \
../isOperation.c \
../main.c \
../postFix.c \
../priority.c \
../readNumber.c 

OBJS += \
./evalPost.o \
./isOperation.o \
./main.o \
./postFix.o \
./priority.o \
./readNumber.o 

C_DEPS += \
./evalPost.d \
./isOperation.d \
./main.d \
./postFix.d \
./priority.d \
./readNumber.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


