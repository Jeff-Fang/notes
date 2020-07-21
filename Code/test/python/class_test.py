class BaseClass():
    a = [0]

    def __init__(self):
        self.i_a = 0


class Child1(BaseClass):
    a = [1]
    def __init__(self):
        self.i_a = 1

class Child2(BaseClass):
    def __init__(self):
        self.i_a = 2

base = BaseClass()
print(base.a)
c1 = Child1()
print(c1.a)
c2 = Child2()
print(c2.a)
