public class Main {
    public static final int STATIC_CONSTANTA = 10;
    private static String static_var;


    public static void main(String[] args) {
        final int CONSTANTA = 100;
        int perem  = CONSTANTA * 2;
        static_var = "Static var";
      

        System.out.println("Статическая константа класса Main : " + STATIC_CONSTANTA );
        System.out.println("Статическая переменная класса Main : " + static_var );
        System.out.println("Нестатическая константа : " + CONSTANTA );
        System.out.println("Нестатическая переменная : " + perem);
    }
}
