package VendingMachine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// ===================== VendingMachine 본체 =====================
public class VendingMachine {
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
            System.out.println(amount + "원이 투입되었습니다. 현재 잔액: " +
                    this.currentBalance + "원");
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
                    System.out.println(drinkName + "이(가) 나왔습니다. (유통기한: " +
                            date + ")");
                    System.out.println("남은 잔액: " + this.currentBalance + "원");
                    return;
                } else {
                    System.out.println(drinkName + ": 금액이 부족합니다. " + (price -
                            this.currentBalance) + "원이 더 필요합니다.");
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
    public int getCurrentBalance() {
        return this.currentBalance;
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
}
