import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        // write your code here
        int[] array = { 23, 43, 76, 82, 25, 18, 15, 57 };
        bubbleSort(array);
    }

    public static void bubbleSort(int[] a) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        System.out.println("sorted array: " + Arrays.toString(a));
    }
}
