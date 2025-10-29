package VendingMachine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// ===================== Drink 클래스 =====================
public class Drink {
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
