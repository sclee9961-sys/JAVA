package Prac;

public class BreakExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                break;  // i가 6일 때 반복을 중단
            }
            System.out.println(i);
        }
        System.out.println("반복문이 종료되었습니다.");
    }
}
