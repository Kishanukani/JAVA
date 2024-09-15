
import java.util.Scanner;

public class input {
    public static void main(String[] args) {
        int[] arr = new int[5];
        Scanner sc = new Scanner(System.in);
        // for (int i : arr) {
        // i = sc.nextInt();
        // }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // for (int i : arr) {/
    }
}
