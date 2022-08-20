import java.util.Scanner;

public class SearchNumber {
    public static void main(String[] args) {

        int myNumber= (int) (Math.random() * 10);
        System.out.println("Я загадал число в диапозоне от 0 до 10 и Вам предстоит угадать это число !");
        Scanner scanner = new Scanner(System.in);
        int userNumber;
        do{
            System.out.print("Введите ваш вариант : ");
            userNumber = scanner.nextInt() ;
            if(userNumber < myNumber){
                System.out.println("Нет, немного больше ...");
            }else if(userNumber > myNumber){
                System.out.println("Нет, немного меньше ...");
            }
        }while (myNumber != userNumber);
        System.out.println("Вы угадали, ПОЗДРАВЛЯЮ !");
    }
}
