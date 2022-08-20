import java.util.Scanner;

public class Resolve {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите x :");
        int x = scanner.nextInt();
        if(x != 0) {
            int y = ( 2 * x + 5 * x ) / 4 * x;
            System.out.println("Результат y = " + y);
        }else {
            System.out.println("Ошибка, x не должен быть равен 0");
        }
    }
}
