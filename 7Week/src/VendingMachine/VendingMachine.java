package VendingMachine;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Comparator;

public class VendingMachine {

    // ===================== Drink 클래스 =====================
    public static class Drink {
        private String name;
        private int price;
        private List<LocalDate> expirationDates; // 유통기한 리스트 (재고별로 다름)

        public Drink(String name, int price, int stock, LocalDate baseDate) {
            this.name = name;
            this.price = price;
            this.expirationDates = new ArrayList<>();

            // 예시로 stock 개수만큼 유통기한 자동 추가 (5일씩 차이)
            for (int i = 0; i < stock; i++) {
                this.expirationDates.add(baseDate.plusDays(i * 5));
            }
            sortByDate();
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getStock() {
            return expirationDates.size();
        }

        // 유통기한 추가
        public void addExpiration(LocalDate date) {
            this.expirationDates.add(date);
            sortByDate();
        }

        // 가장 오래된 음료 배출
        public LocalDate dispense() {
            if (!expirationDates.isEmpty()) {
                return expirationDates.remove(0);
            }
            return null;
        }

        // 오래된 순으로 정렬
        private void sortByDate() {
            expirationDates.sort(Comparator.naturalOrder());
        }

        public List<LocalDate> getExpirationDates() {
            return expirationDates;
        }

        // 재고 추가
        public void addStock(int quantity, LocalDate startDate) {
            for (int i = 0; i < quantity; i++) {
                this.expirationDates.add(startDate.plusDays(i * 5));
            }
            sortByDate();
        }
    }

    // ===================== VendingMachine 본체 =====================
    private List<Drink> inventory;
    private int currentBalance;

    public VendingMachine() {
        this.inventory = new ArrayList<>();
        this.currentBalance = 0;
    }

    public void addDrink(Drink drink) {
        this.inventory.add(drink);
    }

    public void insertCoin(int amount) {
        if (amount > 0) {
            this.currentBalance += amount;
            System.out.println(amount + "원이 투입되었습니다. 현재 잔액: " + this.currentBalance + "원");
        }
    }

    public void selectDrink(String drinkName) {
        for (Drink drink : inventory) {
            if (drink.getName().equalsIgnoreCase(drinkName)) {
                int price = drink.getPrice();

                if (drink.getStock() <= 0) {
                    System.out.println(drinkName + ": 죄송합니다. 재고가 없습니다.");
                    return;
                }

                if (this.currentBalance >= price) {
                    this.currentBalance -= price;
                    LocalDate date = drink.dispense();
                    System.out.println(drinkName + "이(가) 나왔습니다. (유통기한: " + date + ")");
                    System.out.println("남은 잔액: " + this.currentBalance + "원");
                    return;

                } else {
                    System.out.println(drinkName + ": 금액이 부족합니다. " + (price - this.currentBalance) + "원이 더 필요합니다.");
                    return;
                }
            }
        }
        System.out.println("죄송합니다. " + drinkName + "은(는) 판매하지 않습니다.");
    }

    public int returnChange() {
        int change = this.currentBalance;
        this.currentBalance = 0;
        System.out.println("\n잔돈 " + change + "원을 반환합니다.");
        return change;
    }

    public void displayInventory() {
        System.out.println("\n========== 자판기 메뉴 ==========");
        for (Drink drink : inventory) {
            System.out.printf("%s (%d원) - 재고: %d개%n",
                    drink.getName(), drink.getPrice(), drink.getStock());
            System.out.println("  유통기한 목록: " + drink.getExpirationDates());
        }
        System.out.println("---------------------------------");
        System.out.println("현재 투입된 금액: " + this.currentBalance + "원");
        System.out.println("=================================\n");
    }

    // ===================== 실행 메인 =====================
    public static void main(String[] args) {
        VendingMachine myVendingMachine = new VendingMachine();

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

        Scanner scanner = new Scanner(System.in);
        String input = "";

        System.out.println("--- 자판기 시뮬레이션 시작 ---\n");

        while (!input.equalsIgnoreCase("종료")) {
            myVendingMachine.displayInventory();
            System.out.println("명령어를 입력하세요:");
            System.out.println("1.<금액> - 돈 투입 (예: 1.1000)");
            System.out.println("2.<음료명> - 음료 선택 (예: 2.콜라)");
            System.out.println("3 - 잔돈 반환");
            System.out.println("종료 - 프로그램 종료");
            System.out.print("> ");

            input = scanner.nextLine();

            if (input.startsWith("1.")) { // 돈 투입
                String amountStr = input.substring(2).trim();
                int amount = Integer.parseInt(amountStr);
                myVendingMachine.insertCoin(amount);

            } else if (input.startsWith("2.")) { // 음료 선택
                String drinkName = input.substring(2).trim();
                myVendingMachine.selectDrink(drinkName);

            } else if (input.equalsIgnoreCase("3.") || input.equalsIgnoreCase("3")) {
                myVendingMachine.returnChange();

            } else if (!input.equalsIgnoreCase("종료")) {
                System.out.println("올바른 명령어를 입력하세요.");
            }
            System.out.println();
        }

        scanner.close();
        System.out.println("--- 시뮬레이션 종료 ---");
    }
}