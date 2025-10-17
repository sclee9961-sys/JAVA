package Exam;

import java.util.Scanner;

public class Question {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println(("정수를 입력하시오:"));
        int n = sc.nextInt();

        if (n % 2 == 0){
            System.out.println("짝수 :");
        } else {
            System.out.println("홀수 :");
        }
    }
}
