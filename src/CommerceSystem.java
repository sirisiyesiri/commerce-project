import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CommerceSystem {
    Scanner scanner = new Scanner(System.in);
    Category category = new Category();

    CommerceSystem(Category category) {
        this.category = category;
    }


    public void start() {
        // 플랫폼 메인 화면 출력
        ArrayList<String> arrayList = category.getCategoryList();
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for(int i = 1; i <= category.categorycount(); i++) {
            System.out.println(i + ". " + arrayList.get(i-1));
        }
        System.out.printf("0. %-5s | %-10s", "종료", "프로그램 종료.");
        System.out.println();
        // 영어와 한글의 글자 크기 출력이 다름

    }

    public int inputCategoryChoice() {  // 카테고리 선택
        while(true) {
            try {
                System.out.print("상품의 카테고리를 입력해주세요.(0입력 시 프로그램 종료) : ");
                int categoryChoice = scanner.nextInt();
                System.out.println();
                if(categoryChoice < 0 || categoryChoice > category.categorycount()) {
                    throw new IllegalArgumentException("잘못된 카테고리 번호입니다.(0 ~ " + category.categorycount() + " 사이의 숫자를 입력하세요)");
                }
                return categoryChoice;
            } catch (InputMismatchException e1) {
                System.out.println("잘못 입력하셨습니다.");
            } catch (IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }


    public int inputProductChoice(int categoryChoice) {  // 상품 번호 선택
        while(true) {
            try {
                System.out.print("선택하신 상품 번호를 입력해주세요(0입력 시 뒤로가기) : ");
                int productChoice = scanner.nextInt();
                if(productChoice < 0 || productChoice > category.getEachListSize(categoryChoice)) {    // 각 productList 사이즈를 반환하는 함수를 이용해 최대값 관련 조건을 출력해야겠음.
                    throw new IllegalArgumentException("잘못된 상품 번호입니다.(0 ~ " + category.getEachListSize(categoryChoice) + " 사이의 숫자를 입력하세요)");
                }
                return productChoice;
            } catch (InputMismatchException e1) {
                System.out.println("잘못 입력하셨습니다.");
            } catch (IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
                System.out.println();
            }
        }
    }

}
