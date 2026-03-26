import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 25);
        Product product2 = new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30);
        Product product3 = new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 15);
        Product product4 = new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 50);
        ArrayList<Product> arraylist = new ArrayList<>();

        arraylist.add(product1);
        arraylist.add(product2);
        arraylist.add(product3);
        arraylist.add(product4);

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        for(Product product : arraylist) {
            System.out.println();
        }



    }
}
