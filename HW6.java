class Student{

    private String firstName, lastName, group;
    private double averageMark;

    public Student(String firstName, String lastName, String group, double averageMark) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.averageMark = averageMark;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public double getAverageMark() {
        return averageMark;
    }


    public double getScholarship(){
        double sum;
        if( Double.compare(this.getAverageMark(), 5.0) == 0 ){
            sum = 100.0;
        }else{
            sum = 80.0;
        }
        return sum;
    }

    public String toString(){
        return this.getFirstName() + " " + this.getLastName() + " " + this.getGroup();
    }

}

class Aspirant extends Student{
    private String scientificWork;

    public Aspirant(String firstName, String lastName, String group, double averageMark, String scientificWork) {
        super(firstName, lastName, group, averageMark);
        this.scientificWork = scientificWork;
    }

    @Override
    public double getScholarship(){
        double sum;

        if( Double.compare(this.getAverageMark(), 5.0) == 0 ){
            sum = 200.0;
        }else{
            sum = 180.0;
        }
        return sum;
    }
}


public class HW6 {

    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student("Александр", "Петров", "ФПМ № 2", 3.0 );
        students[1] = new Aspirant("Михаил", "Потапов", "Аспирантура", 3.7, "Поиски смысла жизни в предпенсионном возрасте" );
        students[2] = new Student("Андрей", "Ночкин", "ФПМ № 2", 5.0 );
        students[3] = new Student("Владимир", "Иванов", "ФПМ № 2", 4.5 );
        students[4] = new Aspirant("Ivan", "Vladimirov", "Аспирантура", 5.0, "Есть ли жизнь на Марсе ?" );

        for (int i = 0; i < students.length; i++) {
            System.out.println( students[i] +  " : сумма стипендии = " +  students[i].getScholarship()
            );
        }
    }
}