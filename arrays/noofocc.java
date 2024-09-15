
import java.util.Scanner;

public class noofocc {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 1, 1, 4, 3, 9 };
        Scanner sc = new Scanner(System.in);
        int a = 0;
        System.out.println("Enter the number to search : ");
        int n = sc.nextInt();
        for (int i = 0; i < arr.length; i++) {
            if (n == arr[i]) {
                a++;
            }
        }
        System.out.println(a);
    }
}
