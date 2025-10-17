package Prac;

import java.util.Scanner;

public class InputPractice {
    public static void main(String[] args) {
        /*
            첫 번째 숫자: 1 //입력
            두 번째 숫자: 2 //입력
            연산자(+, -, *, /): + //입력
            결과: 1 + 2
         */
        Scanner sc = new Scanner(System.in);
        System.out.print("첫 번째 숫자: ");
        int first = sc.nextInt();
        System.out.print("두 번째 숫자: ");
        int second = sc.nextInt();
        System.out.print("연산자(+, -, *, /): ");
        String o = sc.next();

        System.out.println("결과: " + first + " " + o + " " + second);
    }
}
