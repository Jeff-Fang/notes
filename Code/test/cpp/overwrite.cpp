#include <iostream>

class A {
public:
    virtual void call() = 0;
};

class B : A {
public:
    void call() override;
};


class C : public B {
public:
    void call() override;
};

// void A::call() {
//     std::cout << "A is called" << std::endl;
// }


void B::call() {
    std::cout << "B is called" << std::endl;
}


void C::call() {
    std::cout << "C is called" << std::endl;
    B::call();
}


int main() {
    B b;
    b.call();
    std::cout << "------------" << std::endl;


    C c;
    c.call();
    std::cout << "------------" << std::endl;

    B *c_b = new C();
    c_b->call();
    std::cout << "------------" << std::endl;

    return 0;
}
