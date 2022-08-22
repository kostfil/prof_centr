public class multiplicationTable {
    public static void main(String[] args) {
        int dem = 7;
        for (int j = 2; j <=10 ; j++) {
            for (int i = 1; i <= 10; i++) {
                System.out.println(j + " x " + i + " = " + i * j);
            }
            System.out.println();
        }
    }
}
