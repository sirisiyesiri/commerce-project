import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommerceSystem commerceSystem = new CommerceSystem();

        while(true) {
            commerceSystem.start(); // 커머스 플랫폼 메인 화면 출력

            int categoryChoice = commerceSystem.inputCategoryChoice();  // 카테고리 선택
            if(categoryChoice != 0) {
                if(categoryChoice >= 1 && categoryChoice <= commerceSystem.getCategoryCount()) {
                    while(true) {
                        int categoryMenuChoice = commerceSystem.inputCategoryMenu(categoryChoice);  // 카테고리 리스트 조회 선택
                        if(categoryMenuChoice != 0) {
                            Product productChoice = commerceSystem.printProductList(categoryChoice, categoryMenuChoice);    // 선택한 카테고리의 상품 출력

                            System.out.println();

                            if(productChoice != null) {
                                int addShoppingCart = commerceSystem.inputAddShopping();    // 장바구니 추가 확인
                                if(addShoppingCart == 1) {
                                    commerceSystem.addShoppingCart(productChoice);  // 장바구니 추가 기능
                                }
                            } else {
                                continue;
                            }
                            break;
                        } else {
                            break;
                        }
                    }
                } else if(categoryChoice == (commerceSystem.getCategoryCount()+1)) {    // 장바구니 확인
                    int orderChoice = commerceSystem.inputProductOrder();   // 장바구니 주문
                    if(orderChoice == 1) {
                        Grade grade = commerceSystem.inputGrade();  // 고객 등급 입력
                        commerceSystem.orderComplete(grade);    // 등급 별 할인 처리 후 주문 완료
                    } else if(orderChoice == 2) {
                        commerceSystem.cancelShoppingCartProduct(); // 장바구니 상품 취소
                    }
                } else if(categoryChoice == (commerceSystem.getCategoryCount()+2)) {
                    System.out.println("주문을 취소하였습니다.");
                    System.out.println();
                }
                else {
                    int choiceManagerFunction = commerceSystem.ChoiceManagerFunction(); // 관리자 모드 기능 선택

                    if(choiceManagerFunction != 0) {
                        switch (choiceManagerFunction) {
                            case 1:
                                commerceSystem.addCategoryProduct();    // 상품 추가 기능
                                break;
                            case 2:
                                commerceSystem.correctProduct();    // 상품 수정 기능
                                break;
                            case 3:
                                commerceSystem.deleteProduct(); // 상품 삭제 기능
                                break;
                            case 4:
                                commerceSystem.allProductStatus();  // 전체 상품 리스트 출력
                                break;
                        }
                    }
                }

            } else {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }
        }
    }
}
