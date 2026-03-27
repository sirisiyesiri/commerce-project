import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommerceSystem commerceSystem = new CommerceSystem();

        // arraylist에 상품 정보 저장
        commerceSystem.start("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 25);
        commerceSystem.start("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30);
        commerceSystem.start("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 15);
        commerceSystem.start("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 50);

        commerceSystem.printProduct();  // 상품 정보 출력

        int choice = commerceSystem.inputChoice(); // 상품 선택(0 입력 시 프로그램 종료)

        if(choice == 0) {
            System.out.println("커머스 플랫폼을 종료합니다.");
            return;
        }



    }
}
