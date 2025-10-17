package Prac;

public class MaxArray {
    public static void main(String[] args) {
        int[] numbers = {10, 50, 20, 30, 40};
        int max = numbers[0];
        for (int i=1; i < numbers.length; i++) {
            System.out.println((i+1)+"번째 "+" 값="+numbers[i]);
            if ( numbers[i] > max ){
                System.out.println(numbers[i] + " / " + max + " = max 가 더 작습니다.");
                max = numbers[i];
            }else{
                System.out.println(numbers[i] + " / " + max + " = max 가 더 큽니다.");
            }
        }
        System.out.println("최대값 = "+max);
    }
}

