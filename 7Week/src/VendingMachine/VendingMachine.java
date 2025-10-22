package VendingMachine;
import java.util.ArrayList;
import java.util.List;


public class VendingMachine {


    public static class Drink {
        private String name;
        private int price;
        private int stock;


        public Drink(String name, int price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }


        public String getName() {
            return name;
        }


        public int getPrice() {
            return price;
        }


        public int getStock() {
            return stock;
        }


        public void dispense() {
            if (this.stock > 0) {
                this.stock--;
            }
        }


        public void addStock(int quantity) {
            this.stock += quantity;
        }
    }


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
                    drink.dispense();
                    System.out.println(drinkName + "이(가) 나왔습니다.");
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
        }
        System.out.println("---------------------------------");
        System.out.println("현재 투입된 금액: " + this.currentBalance + "원");
        System.out.println("=================================\n");
    }


    public static void main(String[] args) {
        VendingMachine myVendingMachine = new VendingMachine();


        Drink coke = new Drink("콜라", 1200, 5);
        Drink sprite = new Drink("사이다", 1200, 3);
        Drink water = new Drink("생수", 800, 10);
        Drink juice = new Drink("오렌지주스", 1500, 0); // 재고 없음 테스트용


        myVendingMachine.addDrink(coke);
        myVendingMachine.addDrink(sprite);
        myVendingMachine.addDrink(water);
        myVendingMachine.addDrink(juice);


        System.out.println("--- 자판기 시뮬레이션 시작 ---\n");


        myVendingMachine.displayInventory();
        myVendingMachine.insertCoin(1000);
        myVendingMachine.insertCoin(500); // 현재 잔액: 1500원
        myVendingMachine.selectDrink("콜라"); // 콜라 1200원, 잔액 1500 -> 300원
        myVendingMachine.selectDrink("오렌지주스");
        myVendingMachine.selectDrink("사이다"); // 사이다 1200원, 잔액 300원 -> 부족
        myVendingMachine.insertCoin(1000); // 현재 잔액: 1300원 (300 + 1000)
        myVendingMachine.selectDrink("사이다"); // 사이다 1200원, 잔액 1300 -> 100원
        myVendingMachine.returnChange();


        myVendingMachine.displayInventory();
        System.out.println("--- 시뮬레이션 종료 ---");
    }
}


