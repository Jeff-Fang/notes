# run with python3

class Human:
    species = "H. sapiens"

    def __init__(self, name):
        self.name = name
        self.age = 0

    def say(self, msg):
        return "{name}: {message}".format(name=self.name, message=msg)

    @classmethod
    def get_species(cls):
        return cls.species

    @staticmethod
    def grunt():
        return "*grunt*"

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, age):
        self._age = age

    @age.deleter
    def age(self):
        del self._age

i = Human(name="Ian")
print(i.say("hi"))

j = Human("Joel")
print(j.say("hello"))

i.get_species()

Human.species = "H. neanderthalensis"
i.get_species()
j.get_species()

Human.grunt()
