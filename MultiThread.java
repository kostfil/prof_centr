class Calculate extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if( i % 10 == 0){
                System.out.println(i);
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultiThread {
    public static void main(String[] args) throws InterruptedException {
        Calculate calculate1 = new Calculate();
        Calculate calculate2 = new Calculate();
        Calculate calculate3 = new Calculate();
        System.out.println("Start !");
        calculate1.start();
        calculate2.start();
        calculate3.start();

    }
}