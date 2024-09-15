package arrays;

public class ex2 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, -3, 5, 7 };

        int a = sumis(arr);
        System.out.println("Sum is" + a);
        int b = prod(arr);
        System.out.println("Prod is :" + b);
        double c = (double) a / arr.length;
        System.out.println("Avg is :" + c);
    }

    public static int sumis(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }

    public static int prod(int[] arr) {
        int prod = 1;
        for (int i = 0; i < arr.length; i++) {
            prod *= arr[i];
        }

        return prod;
    }
}
