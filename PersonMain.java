class Person {
    private String fullName;
    private int age;

    public Person(){};

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public void move(){
        System.out.println(fullName + " говорит");
    }
    public void talk(){
        System.out.println(fullName + " говорит");
    }
}

public class PersonMain {

    public static void main(String[] args) {
        Person person1 = new Person();
        person1.move();
        person1.talk();

        Person person2 = new Person("Филин Константин", 60);
        person2.talk();
        person2.move();

    }
}