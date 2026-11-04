package DSA;

public class SimpleArr {
    public static void main (String[] args) {
        int [] arr = {12, 45, 2, 67, 34, 89, 23};
        int size = arr.length;
        int max = arr[0];
        int min = arr[0];
        int total = 0;
        double avg = 0;

        for (int i = 0; i < size; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
            total += arr[i];
        }
        avg = (double) total / size;

        System.out.println(max);
        System.out.println(min);
        System.out.println(avg);
    }
}