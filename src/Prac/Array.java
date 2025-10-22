package Prac;

public class Array {
    public static void main(String[] args) {

        int[] numbers = new int[5];
        int[] array = {10, 20, 30, 40, 50};

        System.out.println(numbers.length);
        System.out.println(array.length);

        System.out.println(array[0]);
        //System.out.println(array[4]);

        int[] oldArray = {1, 2, 3};      // 기존 배열
        int[] newArray = new int[5];     // 새로운 배열 생성
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length); // 배열 복사

        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]);  // 배열의 각 요소 출력
        }
    }
}
