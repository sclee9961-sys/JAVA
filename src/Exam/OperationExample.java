package Exam;

public class OperationExample {
    public static void main(String[] args){
        int x = 5;
        int y = ++x;  // x는 먼저 증가하고, y에 6이 저장됨
        boolean flag = false;
        boolean notFlag = !flag;  // flag가 false일 때, notFlag는 true가 됨

        System.out.println(x);
        System.out.println(y);
        System.out.println(flag);
        System.out.println(notFlag);

        boolean isLess = (5 < 10);  // 결과: true
        boolean isGreaterOrEqual = (10 >= 10);  // 결과: true

        System.out.println(isLess);
        System.out.println(isGreaterOrEqual);

        boolean isEqual = (5 == 5);  // 결과: true
        boolean isNotEqual = (5 != 3);  // 결과: true
        System.out.println(isEqual);
        System.out.println(isNotEqual);


    }
}
