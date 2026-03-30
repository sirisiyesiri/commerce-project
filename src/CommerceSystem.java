import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// 상품 관리 및 사용자 입력 처리 class
public class CommerceSystem {
    Scanner scanner = new Scanner(System.in);
    Category category = new Category();
    ShoppingCart shoppingCart = new ShoppingCart();


    public void start() {
        // 플랫폼 메인 화면 출력
        ArrayList<String> arrayList = category.getCategoryNameList();
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        for(int i = 1; i <= category.categorycount(); i++) {
            System.out.println(i + ". " + arrayList.get(i-1));
        }
        System.out.printf("0. %-8s | 프로그램 종료.", "종료");
        System.out.println();
        System.out.println();   // 사용자 UI를 위한 줄바꿈
        // 영어와 한글의 글자 크기 출력이 다름

        if(shoppingCart.notShoppingCatrEmpty()) {
            System.out.println("[ 주문 관리 ]");
            System.out.printf((category.categorycount()+1) + ". %-8s | 장바구니를 확인 후 주문합니다.", " 장바구니 확인");
            System.out.println();
            System.out.printf((category.categorycount()+2) + ". %-8s | 진행중인 주문을 취소합니다.", " 주문 취소");
            System.out.println();
            System.out.println();   // 사용자의 UI를 위한 줄바꿈
        }

    }

    public int inputCategoryChoice() {// 카테고리 선택
        if(shoppingCart.notShoppingCatrEmpty()) {
            while(true) {
                try {
                    System.out.print("상품의 카테고리를 입력해주세요.(0입력 시 프로그램 종료) : ");
                    int categoryChoice = scanner.nextInt();
                    System.out.println();
                    if(categoryChoice < 0 || categoryChoice > (category.categorycount()+2)) {
                        throw new IllegalArgumentException("잘못된 카테고리 번호입니다.(0 ~ " + (category.categorycount()+2) + " 사이의 숫자를 입력하세요)");
                    }
                    return categoryChoice;
                } catch (InputMismatchException e1) {
                    System.out.println("잘못 입력하셨습니다.");
                } catch (IllegalArgumentException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        } else {
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

    public void printProductList(int categoryChoice) {  // category별 list 출력
        ArrayList<Product> arrayList = category.getCategoryList(categoryChoice);
        int i = 1;

        System.out.println("[ " + category.getCategoryName(categoryChoice) + " 카테고리 ]");
        for(Product product : arrayList) {
            System.out.printf(i + ". %-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() + "개", product.getProductName(), product.getPrice(), product.getDescription());
            System.out.println();
            // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
            // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
            i += 1;
        }
        if(arrayList.isEmpty()) {
            System.out.println("상품이 없습니다.");
        }
        System.out.println("0. 뒤로가기");
        System.out.println();
    }

    public void printChoiceProduct(int categoryChoice, int productChoice) {
        Product product = category.getChoiceProductInformation(categoryChoice, productChoice);
        System.out.print("선택한 상품: ");
        System.out.printf("%-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() +"개", product.getProductName(), product.getPrice(), product.getDescription() );
        System.out.println();
        System.out.println();   // 반복 시 사용자 UI를 위해 한 번 더 줄바꿈
    }

    public int getCategoryCount() {
        return category.categorycount();
    }

    public int inputAddShopping() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.printf("%-8s %-8s", "1. 확인", "2. 취소");
        System.out.println();
        while(true) {
            System.out.print("선택 : ");
            try {
                int addShoppingListChoice = scanner.nextInt();
                System.out.println();   // 사용자 UI를 위한 줄바꿈
                if(addShoppingListChoice != 1 && addShoppingListChoice != 2) {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
                return addShoppingListChoice;
            } catch (InputMismatchException e1) {
                System.out.println("잘못된 입력입니다.");
            } catch (IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public void addShoppingCart(int categoryChoice, int productChoice) {
        Product product = category.getChoiceProductInformation(categoryChoice, productChoice);
        System.out.print("주문하시고 싶은 수량 : ");
        int count = scanner.nextInt();
        product.setCartCount(count);
        shoppingCart.addShoppingCart(product);
    }

    public int inputProductOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        System.out.println("[ 장바구니 내역 ]");
        for(Product product : shoppingCart.getShoppingList()) {
            System.out.printf("%-15s | %-10s | %-15s | 수량: " + product.getCartCount() +"개",
                    product.getProductName(), product.getPrice(), product.getDescription());
            System.out.println();
        }
        System.out.println();

        System.out.println("[ 총 주문 금액 ]");
        String totalPrice = shoppingCart.totalPrice();
        System.out.println(totalPrice + "원");
        System.out.println();

        System.out.printf("%-8s %-8s", "1. 주문 확정", "2. 메인으로 돌아가기");
        System.out.println();

        while(true) {
            System.out.print("선택 : ");
            try {
                int orderChoice = scanner.nextInt();
                System.out.println();   // 사용자 UI를 위한 줄바꿈
                if(orderChoice != 1 && orderChoice != 2) {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
                return orderChoice;
            } catch (InputMismatchException e1) {
                System.out.println("잘못된 입력입니다.");
            } catch (IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
            }
        }

    }

    public void orderComplete() {
        int index = 0;
        System.out.println("주문이 완료되었습니다! 총 금액: " + shoppingCart.totalPrice() +"원");
        for(Product product : shoppingCart.getShoppingList()) {
            System.out.println(product.getProductName() + "재고가 " + product.getStockQuantity() + "개 -> "
                    + (product.getStockQuantity()-product.getCartCount()) + "개로 업데이트되었습니다.");
            shoppingCart.EditStockQuantity(index++, product.getCartCount());
        }
        shoppingCart.resetShoppingList();
        System.out.println();
    }

}
