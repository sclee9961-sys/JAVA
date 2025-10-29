package VendingMachine;
import java.time.LocalDate;
import java.util.Scanner;
public class VendingMachineSimulation {
    // ===================== 실행 메인 =====================
    public static void main(String[] args) {
        VendingMachine myVendingMachine = new VendingMachine();
        System.out.println("--- 자판기 시뮬레이션 시작 ---\n");
        Drink coke = new Drink("콜라", 1200, 5, LocalDate.of(2025, 11, 1));
        Drink sprite = new Drink("사이다", 1200, 3, LocalDate.of(2025, 11, 5));
        Drink water = new Drink("생수", 800, 10, LocalDate.of(2025, 10, 25));
        Drink juice = new Drink("오렌지주스", 1500, 0, LocalDate.of(2025, 12, 1)); // 재고 없음
        myVendingMachine.addDrink(coke);
        myVendingMachine.addDrink(sprite);
        myVendingMachine.addDrink(water);
        myVendingMachine.addDrink(juice);
        Scanner scanner = new Scanner(System.in);
        String input = "";
        // 원본 파일에 중복되어 있던 "시뮬레이션 시작" 출력문을 하나로 정리했습니다.
        // System.out.println("--- 자판기 시뮬레이션 시작 ---\n");
        while (!input.equalsIgnoreCase("종료")) {
            // 현재 메뉴 및 잔액 표시
            myVendingMachine.displayInventory();
            System.out.println("---------------------------------");
            System.out.println("1. 돈 투입 (금액 입력) | 2. 음료 선택 (이름 입력) | 3. 잔 돈 반환 (반환 입력) | 4. 종료");
                    System.out.print("명령을 입력하세요: ");
            input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("종료")) {
                break;
            }
            try {
                if (input.startsWith("1.")) { // 돈 투입
                    // 입력에서 '1.'을 제거하고 금액만 추출
                    String amountStr = input.substring(2).trim();
                    int amount = Integer.parseInt(amountStr);
                    myVendingMachine.insertCoin(amount);
                } else if (input.startsWith("2.")) { // 음료 선택
                    String drinkName = input.substring(2).trim();
                    myVendingMachine.selectDrink(drinkName);
                } else if (input.equalsIgnoreCase("3.") || input.equalsIgnoreCase("반환"))
                { // 잔돈 반환
                    myVendingMachine.returnChange();
                } else {
                    System.out.println("잘못된 명령 형식입니다. '1.1000' 또는 '2.콜라' 형 식으로 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("금액은 숫자로만 입력해야 합니다.");
            }
        }
        // 종료 시 남은 잔돈 반환
        if (myVendingMachine.getCurrentBalance() > 0) {
            myVendingMachine.returnChange();
        }
        scanner.close();
        System.out.println("\n--- 시뮬레이션 종료 ---");
    }
}