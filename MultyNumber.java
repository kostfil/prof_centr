public class MultyNumber {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        int multi = 1;
        for (int i = 0; i < arr.length ; i++) {
            multi *= arr[i];
        }
        System.out.println("Произведение элементов массива: " + multi);
    }
}
