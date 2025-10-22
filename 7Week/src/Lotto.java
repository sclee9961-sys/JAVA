import java.util.ArrayList;
import java.util.Collections;

public class Lotto {
    public static void main(String[] args){
        ArrayList<Integer> lottoNumbers = new ArrayList<>();
        for (int i=1; i<=45; i++){
            lottoNumbers.add(i);
        }
        Collections.shuffle(lottoNumbers);
        System.out.print("이번주 로또 번호는 ");
        for (int i=0; i<5; i++){
            System.out.print(lottoNumbers.get(i) + ",");
        }
        System.out.println("보너스 번호 "+ lottoNumbers.get(5) + "입니다.");

    }
}