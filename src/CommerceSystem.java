import com.sun.security.jgss.GSSUtil;

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
        System.out.printf("0. %-8s | 프로그램 종료.\n", "종료");
        System.out.printf((category.categorycount()+3) + ". %-8s", "관리자 모드\n");
        System.out.println();
        // 영어와 한글의 글자 크기 출력이 다름

        if(shoppingCart.notShoppingCatrEmpty()) {
            System.out.println("[ 주문 관리 ]");
            System.out.printf((category.categorycount()+1) + ". %-8s | 장바구니를 확인 후 주문합니다.\n", " 장바구니 확인");
            System.out.printf((category.categorycount()+2) + ". %-8s | 진행중인 주문을 취소합니다.\n", " 주문 취소");
            System.out.println();
        }

    }

    public int inputCategoryChoice() {// 카테고리 선택
        if(shoppingCart.notShoppingCatrEmpty()) {
            while(true) {
                try {
                    System.out.print("상품의 카테고리를 입력해주세요.(0입력 시 프로그램 종료) : ");
                    int categoryChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if(categoryChoice < 0 || categoryChoice > (category.categorycount()+3)) {
                        throw new IllegalArgumentException("잘못된 카테고리 번호입니다.(0 ~ " + (category.categorycount()+2) + " 사이의 숫자를 입력하세요)");
                    }
                    return categoryChoice;
                } catch (NumberFormatException e1) {
                    System.out.println("정수를 입력해주세요.");
                } catch (IllegalArgumentException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        } else {
            while(true) {
                try {
                    System.out.print("상품의 카테고리를 입력해주세요.(0입력 시 프로그램 종료) : ");
                    int categoryChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if(categoryChoice < 0 || (categoryChoice > category.categorycount() && categoryChoice < (category.categorycount()+3))) {
                        throw new IllegalArgumentException("잘못된 카테고리 번호입니다.(0 ~ " + category.categorycount() + " 사이의 숫자를 입력하세요)");
                    }
                    return categoryChoice;
                } catch (NumberFormatException e1) {
                    System.out.println("정수를 입력해주세요.");
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
                int productChoice = Integer.parseInt(scanner.nextLine());
                if(productChoice < 0 || productChoice > category.getEachListSize(categoryChoice)) {    // 각 productList 사이즈를 반환하는 함수를 이용해 최대값 관련 조건을 출력해야겠음.
                    throw new IllegalArgumentException("잘못된 상품 번호입니다.(0 ~ " + category.getEachListSize(categoryChoice) + " 사이의 숫자를 입력하세요)");
                }
                return productChoice;
            } catch (NumberFormatException e1) {
                System.out.println("정수를 입력해주세요.");
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
            System.out.printf(i + ". %-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() + "개\n", product.getProductName(), product.getPrice(), product.getDescription());
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
        System.out.printf("%-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() +"개\n", product.getProductName(), product.getPrice(), product.getDescription() );
        System.out.println();
    }

    public int getCategoryCount() {
        return category.categorycount();
    }

    public int inputAddShopping() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.printf("%-8s %-8s", "1. 확인", "2. 취소\n");
        while(true) {
            System.out.print("선택 : ");
            try {
                int addShoppingListChoice = Integer.parseInt(scanner.nextLine());
                System.out.println();   // 사용자 UI를 위한 줄바꿈
                if(addShoppingListChoice != 1 && addShoppingListChoice != 2) {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
                return addShoppingListChoice;
            } catch (NumberFormatException e1) {
                System.out.println("정수를 입력해주세요.");
            } catch (IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public void addShoppingCart(int categoryChoice, int productChoice) {
        Product product = category.getChoiceProductInformation(categoryChoice, productChoice);
       while(true) {
           try {
               System.out.print("주문하시고 싶은 수량 : ");
               int count = Integer.parseInt(scanner.nextLine());
               if(count < 1 || count > product.getStockQuantity()) {
                   throw new IllegalArgumentException("주문가능 수량 : 1 ~ " + product.getStockQuantity() + "개");
               }
               product.setCartCount(count);
               shoppingCart.addShoppingCart(product);
               return;
           } catch (NumberFormatException e1) {
               System.out.println("정수를 입력해주세요.");
           } catch (IllegalArgumentException e2) {
               System.out.println(e2.getMessage());
           }
       }
    }

    public int inputProductOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();

        System.out.println("[ 장바구니 내역 ]");
        for(Product product : shoppingCart.getShoppingList()) {
            System.out.printf("%-15s | %-10s | %-15s | 수량: " + product.getCartCount() +"개\n",
                    product.getProductName(), product.getPrice(), product.getDescription());
        }
        System.out.println();

        System.out.println("[ 총 주문 금액 ]");
        String totalPrice = shoppingCart.totalPrice();
        System.out.println(totalPrice + "원");
        System.out.println();

        System.out.printf("%-8s %-8s", "1. 주문 확정", "2. 메인으로 돌아가기\n");

        while(true) {
            System.out.print("선택 : ");
            try {
                int orderChoice = Integer.parseInt(scanner.nextLine());
                System.out.println();   // 사용자 UI를 위한 줄바꿈
                if(orderChoice != 1 && orderChoice != 2) {
                    throw new IllegalArgumentException("잘못된 입력입니다.");
                }
                return orderChoice;
            } catch (NumberFormatException e1) {
                System.out.println("정수를 입력해주세요.");
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

    public int ChoiceManagerFunction() {
        int count = 0;
        while(count < 3) {
            System.out.print("관리자 비밀번호를 입력해주세요. : ");
            String passward = scanner.nextLine();
            System.out.println();

            if(passward.equals("admin123")) {
                System.out.println("[ 관리자 모드 ]");
                System.out.println("1. 상품 추가");
                System.out.println("2. 상품 수정");
                System.out.println("3. 상품 삭제");
                System.out.println("4. 전체 상품 현황");
                System.out.println("0. 메인으로 돌아가기");
                System.out.println();

                while(true) {
                    try {
                        System.out.print("수행하실 기능을 선택해주세요.(0입력 시 뒤로가기) : ");
                        int managerFunction = Integer.parseInt(scanner.nextLine());
                        System.out.println();
                        if(managerFunction < 0 || managerFunction > 4) {
                            throw new IllegalArgumentException("잘못된 번호 입력입니다.(0 ~ 4 사이의 숫자를 입력하세요)");
                        }
                        return managerFunction;
                    } catch (NumberFormatException e1) {
                        System.out.println("정수를 입력해주세요.");
                    } catch (IllegalArgumentException e2) {
                        System.out.println(e2.getMessage());
                    }
                }

            } else {
                System.out.println("비밀 번호가 올바르지 않습니다.");
                count++;
            }
        }
        return 0;   // 비밀번호 입력 3회 실패 시 메인 메뉴로 돌아가기 이므로 0으로 반환.
    }

    public void addCategoryProduct() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        ArrayList<String> categoryName = category.getCategoryNameList();
        for(int i = 1; i <= category.categorycount(); i++) {
            System.out.println(i + ". " + categoryName.get(i-1));
        }

        while(true) {
            try {
                System.out.print("선택 : ");
                int addProductCategory = Integer.parseInt(scanner.nextLine());
                System.out.println();
                if(addProductCategory < 1 || addProductCategory > category.categorycount()) {
                    throw new IllegalArgumentException("잘못된 번호 입력입니다.(1 ~ " + category.categorycount() + " 사이의 숫자를 입력하세요.)");
                } else {
                    System.out.println("[ " + categoryName.get(addProductCategory-1) + " 카테고리에 상품 추가 ]");
                    System.out.print("상품명을 입력해주세요 : ");
                    String addProductName = scanner.nextLine();
                    System.out.print("가격을 입력해주세요 : ");
                    int price = Integer.parseInt(scanner.nextLine());
                    String addProductPrice = String.format("%,d", price);
                    System.out.print("상품 설명을 입력해주세요 : ");
                    String addProductDescription = scanner.nextLine();
                    System.out.print("재고수량을 입력해주세요 : ");
                    int addProductStockQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.println();

                    System.out.printf("%-15s | %-10s | %-15s | 재고 : " + addProductStockQuantity + "개\n", addProductName, (addProductPrice+"원"), addProductDescription);
                    System.out.println();

                    System.out.println("위 정보로 상품을 추가하시겠습니까?");
                    System.out.printf("%-8s %-8s", "1. 확인", "2. 취소\n");

                    System.out.print("선택 : ");
                    int addChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println();   // 사용자 UI를 위한 줄바꿈
                    if(addChoice != 1 && addChoice != 2) {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }

                    if(addChoice == 1) {
                        Product product = new Product(addProductName, (addProductPrice+"원"), addProductDescription, addProductStockQuantity);
                        category.addProduct(addProductCategory, product);

                        System.out.println("상품이 성공적으로 추가되었습니다!");
                        System.out.println();
                        break;
                    }
                }
            } catch (NumberFormatException e1) {
                System.out.println("정수를 입력해주세요.");
            } catch(IllegalArgumentException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public void correctProduct() {
        System.out.print("수정할 상품명을 입력해주세요 : ");
        String correctProductName = scanner.nextLine();

        Product product = category.searchProduct(correctProductName);   // product는 참조하고 있기 때문에

        if(product != null) {
            System.out.printf("%-15s | %-10s | %-15s | 재고 : " + product.getStockQuantity() + "개\n", product.getProductName(), product.getPrice(), product.getDescription());
            System.out.println();

            System.out.println("수정할 항목을 선택해주세요 : ");
            System.out.println("1. 가격");
            System.out.println("2. 설명");
            System.out.println("3. 재고수량");
            System.out.println();

            while(true) {
                try {
                    System.out.print("선택 : ");
                    int correctChoice = Integer.parseInt(scanner.nextLine());
                    System.out.println();
                    if(correctChoice < 1 || correctChoice > 3) {
                        throw new IllegalArgumentException("잘못된 번호 입력입니다.(1 ~ 3 사이의 숫자를 입력하세요.)");
                    } else {
                        switch(correctChoice) {
                            case 1:
                                System.out.println("현재 가격 : " + product.getPrice());
                                String oldPrice = product.getPrice();
                                System.out.print("새로운 가격을 입력해주세요 : ");
                                int price = Integer.parseInt(scanner.nextLine());
                                String newPrice = String.format("%,d원", price);
                                System.out.println();

                                category.resettingProductPrice(product, newPrice);  // 이 함수를 실행하면 product의 내용도 바뀜

                                System.out.println(product.getProductName() + "의 가격이 " + oldPrice + " → " + newPrice + "으로 수정되었습니다.");
                                System.out.println();
                                return;
                            case 2:
                                System.out.println("현재 설명 : " + product.getDescription());
                                String oldDescription = product.getDescription();
                                System.out.print("새로운 설명을 입력해주세요 : ");
                                String newDescription = scanner.nextLine();

                                category.resettingProductDescription(product, newDescription);

                                System.out.println(product.getProductName() + "의 설명이 '" + oldDescription + "' → '" + newDescription + "'으로 수정되었습니다.");
                                System.out.println();
                                return;
                            case 3:
                                System.out.println("현재 재고수량 : " + product.getStockQuantity() + "개");
                                int oldStockQuantity = product.getStockQuantity();
                                System.out.print("새로운 재고수량을 입력해주세요 : ");
                                int newStockQuantity = Integer.parseInt(scanner.nextLine());

                                category.resettingProductStockQuantity(product, newStockQuantity);

                                System.out.println(product.getProductName() + "의 설명이 " + oldStockQuantity + "개" + " → " + newStockQuantity + "개" + "로 수정되었습니다.");
                                System.out.println();
                                return;
                        }
                    }
                } catch(NumberFormatException e1) {
                    System.out.println("정수를 입력해주세요.");
                } catch (IllegalArgumentException e2) {
                    System.out.println(e2.getMessage());
                }
            }

        } else {
            System.out.println("입력하신 상품이 존재하지 않습니다.");
            return;
        }
    }

    public void deleteProduct() {
        System.out.print("삭제할 상품명을 입력해주세요 : ");
        String deleteProductName = scanner.nextLine();

        Product product = category.searchProduct(deleteProductName);   // product는 참조하고 있기 때문에

        if(product != null) {
            System.out.printf("%-15s | %-10s | %-15s | 재고 : " + product.getStockQuantity() + "개\n", product.getProductName(), product.getPrice(), product.getDescription());
            System.out.println();

            while(true) {
                try {
                    System.out.println("위의 상품을 삭제하시겠습니까?");
                    System.out.printf("%-8s %-8s\n", "1. 삭제", "2. 취소");

                    System.out.print("선택 : ");
                    int deleteProductChoice = Integer.parseInt(scanner.nextLine());
                    if(deleteProductChoice != 1 && deleteProductChoice != 2) {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }
                    if(deleteProductChoice == 1) {
                        String productName = product.getProductName();
                        category.deleteProduct(productName);

                        shoppingCart.deleteShoppingProduct(productName);

                        System.out.println(productName + " 상품이 삭제되었습니다.");
                        System.out.println();
                        break;
                    }
                } catch(NumberFormatException e1) {
                    System.out.println("정수를 입력해주세요.");
                } catch (IllegalArgumentException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        } else {
            System.out.println("입력하신 상품이 존재하지 않습니다.");
            return;
        }
    }

    public void allProductStatus() {
        for(int i = 1; i <= category.categorycount(); i++) {
            ArrayList<Product> list = category.getCategoryList(i);

            System.out.println("[ " + category.getCategoryName(i) + " 카테고리 ]");
            for(Product product : list) {
                System.out.printf("%-15s | %-10s | %-15s | 재고 : " + product.getStockQuantity() + "개\n", product.getProductName(), product.getPrice(), product.getDescription());
            }
            System.out.println();
        }
    }

}
