import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 25);
        Product product2 = new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30);
        Product product3 = new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 15);
        Product product4 = new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 50);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> arraylist = new ArrayList<>();

        arraylist.add(product1);
        arraylist.add(product2);
        arraylist.add(product3);
        arraylist.add(product4);

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        int i = 1;
        for(Product product : arraylist) {
            System.out.printf(i + ". %-15s | %-12s | " + product.getDescription(), product.getProductName(), product.getPrice());
            System.out.println();
            // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
            // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
            i += 1;
        }
        System.out.printf("0. %-14s | %-12s", "종료", "프로그램 종료"); //영어랑 한글의 출력 폭이 달라서 문자열 자리 수 다르게 설정함.
        System.out.println();

        int choice = scanner.nextInt();

        if(choice == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            return;
        }



    }
}
