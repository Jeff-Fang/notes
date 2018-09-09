#!/usr/bin/env python3
class Student(object):
    def __init__(self, name, branch, year):
        self.name = name
        self.branch = branch
        self.year = year
        print("A Student object is created.")

    def print_details(self):
        print("Name:", self.name)
        print("Branch:", self.branch)
        print("Year:", self.year)

std1 = Student('Kushal','CSE','2005')
std1.print_details()
