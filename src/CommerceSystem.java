import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommerceSystem {
    ArrayList<Product> arraylist = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void start(String productName, String price, String description, int stockQuantity) {    // 상품 리스트 저장
        arraylist.add(new Product(productName, price, description, stockQuantity));
    }

    public void printProduct() {    // 상품 리스트 출력
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        int i = 1;
        for(Product product : arraylist) {
            System.out.printf(i + ". %-15s | %-12s | " + product.getDescription(), product.getProductName(), product.getPrice());
            System.out.println();
            // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
            // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
            i += 1;
        }
        if(arraylist.isEmpty()) {
            System.out.println("상품이 없습니다.");
        }
        System.out.printf("0. %-14s | %-12s", "종료", "프로그램 종료"); //영어랑 한글의 출력 폭이 달라서 문자열 자리 수 다르게 설정함.
        System.out.println();
    }

    public int inputChoice() {  // 상품 번호 선택
        while(true) {
            try {
                System.out.println("선택하신 상품 번호를 입력해주세요(0입력 시 프로그램 종료) : ");
                int choice = scanner.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

}
