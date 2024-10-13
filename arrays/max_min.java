package arrays;

public class max_min {
    public static void main(String[] args) {
        int[] arr = { 4, 2, 3, 6, 1, 10 };
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println(max + " " + min);
    }
}
