import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Category {
    private ArrayList<String> categoryName = new ArrayList<>(List.of("전자제품", "의류", "식품"));
    private ArrayList<Product> electronicsList = new ArrayList<>();
    private ArrayList<Product> clothesList = new ArrayList<>();
    private ArrayList<Product> foodList = new ArrayList<>();

    public Category() { // 상품 리스트 셋팅
        electronicsList.add(new Product("Galaxy S24", "1,200,000원", "최신 안드로이드 스마트폰", 25));
        electronicsList.add(new Product("iPhone 15", "1,350,000원", "Apple의 최신 스마트폰", 30));
        electronicsList.add(new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 15));
        electronicsList.add(new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 50));
    }

    public int categorycount() {
        return categoryName.size();
    }

    public ArrayList<String> getCategoryList() {
        return categoryName;
    }


    public void printProduct(int categoryNumber) {    // 상품 리스트 출력
        int i = 1;

        switch (categoryNumber) {
            case 1:
                System.out.println("[ 전자제품 카테고리 ]");
                for(Product product : electronicsList) {
                    System.out.printf(i + ". %-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() + "개", product.getProductName(), product.getPrice(), product.getDescription());
                    System.out.println();
                    // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
                    // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
                    i += 1;
                }
                if(electronicsList.isEmpty()) {
                    System.out.println("상품이 없습니다.");
                }
                System.out.println("0. 뒤로가기");
                System.out.println();
                break;
            case 2:
                System.out.println("[ 의류 카테고리 ]");
                for(Product product : clothesList) {
                    System.out.printf(i + ". %-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() + "개", product.getProductName(), product.getPrice(), product.getDescription());
                    System.out.println();
                    // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
                    // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
                    i += 1;
                }
                if(clothesList.isEmpty()) {
                    System.out.println("상품이 없습니다.");
                }
                System.out.println("0. 뒤로가기");
                System.out.println();
                break;
            case 3:
                System.out.println("[ 식품 카테고리 ]");
                for(Product product : foodList) {
                    System.out.printf(i + ". %-15s | %-10s | %-15s | 재고: " + product.getStockQuantity() + "개", product.getProductName(), product.getPrice(), product.getDescription());
                    System.out.println();
                    // C처럼 문자열 자리 수 맞춰서 출력하는 방법 예시: printf("%10s", "hello");
                    // %10s = 오른쪽 정렬(기본), %-10s = 왼쪽 정렬
                    i += 1;
                }
                if(foodList.isEmpty()) {
                    System.out.println("상품이 없습니다.");
                }
                System.out.println("0. 뒤로가기");
                System.out.println();
                break;
            case 0:
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            default:
        }
    }

    public void printChoiceProduct(int categoryChoice, int productChoice) {
        switch(categoryChoice) {
            case 1:
                System.out.print("선택한 상품: ");
                System.out.printf("%-15s | %-10s | %-15s | 재고: " + electronicsList.get(productChoice-1).getStockQuantity() +"개",
                        electronicsList.get(productChoice-1).getProductName(), electronicsList.get(productChoice-1).getPrice(), electronicsList.get(productChoice-1).getDescription() );
                System.out.println();
                System.out.println();   // 반복 시 사용자 UI를 위해 한 번 더 줄바꿈
                break;
            case 2:
                System.out.printf("%-15s | %-10s | %-15s | 재고: " + clothesList.get(productChoice-1).getStockQuantity() +"개",
                        clothesList.get(productChoice-1).getProductName(), clothesList.get(productChoice-1).getPrice(), clothesList.get(productChoice-1).getDescription() );
                System.out.println();
                System.out.println();
                break;
            case 3:
                System.out.printf("%-15s | %-10s | %-15s | 재고: " + foodList.get(productChoice-1).getStockQuantity() +"개",
                        foodList.get(productChoice-1).getProductName(), foodList.get(productChoice-1).getPrice(), foodList.get(productChoice-1).getDescription() );
                System.out.println();
                System.out.println();
                break;
        }
    }

    public int getEachListSize(int categoryChoice) {
        switch(categoryChoice) {
            case 1:
                return electronicsList.size();
            case 2:
                return clothesList.size();
            case 3:
                return foodList.size();
            default:
                return 0;
        }
    }

}
