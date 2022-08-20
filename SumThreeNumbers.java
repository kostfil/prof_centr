import java.util.Scanner;

public class SumThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число :");
        int x1 = scanner.nextInt();
        System.out.print("Введите второе число :");
        int x2 = scanner.nextInt();
        System.out.print("Введите третье число :");
        int x3 = scanner.nextInt();
        System.out.println("Сумма трех числе = " + sumThreeNumber(x1,x2,x3));
    }

    private static int sumThreeNumber(int x1, int x2, int x3){
        return x1+x2+x3;
    }
}
