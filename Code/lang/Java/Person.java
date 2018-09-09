class Person{
    private String name;
    private int age;
    private char gender;

    public void assignName(String nm) {name = nm;}
    public void assignAge(int a) { age = a ;}
    public void assignGender(char b) { gender = b ;}
    public String toString(){
            return (name+" age is "+age+" sex is "+gender);
    }
};
