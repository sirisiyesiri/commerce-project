import java.util.*;
import java.util.stream.Stream;

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

    public ArrayList<String> getCategoryNameList() {
        return categoryName;
    }

    public String getCategoryName(int categoryNumber) {
        return categoryName.get(categoryNumber-1);
    }

    public ArrayList<Product> getCategoryList(int categoryChoice) {
        switch(categoryChoice) {
            case 1:
                return electronicsList;
            case 2:
                return clothesList;
            case 3:
                return foodList;
        }
        return null;
    }

    public List<Product> printfProductList(int categoryChoice, int choice) {
        ArrayList<Product> arrayList = getCategoryList(categoryChoice);
        List<Product> list = Collections.emptyList();   // 빈 리스트로 list 초기화
        switch (choice) {
            case 1:
                list = arrayList.stream()
                        .toList();
                break;
            case 2:
                System.out.println("[ 100만원 이하 상품 목록 ]");
                list= arrayList.stream()
                        .filter(product -> !product.upper100())
                        .toList();
                break;
            case 3:
                System.out.println("[ 100만원 초과 상품 목록 ]");
                list= arrayList.stream()
                        .filter(product -> product.upper100())
                        .toList();
                break;

        }
        return list;
    }

    public void addProduct(int addProductCategory, Product product) {
        switch(addProductCategory) {
            case 1:
                electronicsList.add(product);
                break;
            case 2:
                clothesList.add(product);
                break;
            case 3:
                foodList.add(product);
                break;
        }
    }

    public Product searchProduct(String searchProductName) {
        return Stream.concat(
                Stream.concat(electronicsList.stream(), clothesList.stream()),
                foodList.stream()
        )
                .filter(product -> product.getProductName().equals(searchProductName))
                .findFirst()
                .orElse(null);

        // Stream.concat(a, b) : 두 스트림 이어붙임
        // .filter() : 조건에 맞는 것만 남김
        // .findFirst() : 첫 번째 요소 Optional로 반환
        // .orElse(null) : 값이 있으면 반환형(Product)으로 반환 없으면 null
    }

    public void resettingProductPrice(Product product, String newPrice) {
        Stream.concat(
                Stream.concat(electronicsList.stream(), clothesList.stream()),
                foodList.stream()
        )
                .filter(p -> p == product)
                .findFirst()
                .ifPresent(p -> p.setPrice(newPrice));

        // .ifPresent : Optional로 받은 값이 존재하면 실행
    }

    public void resettingProductDescription(Product product, String newDescription) {
        Stream.concat(
                Stream.concat(electronicsList.stream(), clothesList.stream()),
                foodList.stream()
        )
                .filter(p -> p == product)
                .findFirst()
                .ifPresent(p -> p.setDescription(newDescription));
    }

    public void resettingProductStockQuantity(Product product, int newStockQuantity) {
        Stream.concat(
                Stream.concat(electronicsList.stream(), clothesList.stream()),
                foodList.stream()
        )
                .filter(p -> p == product)
                .findFirst()
                .ifPresent(p -> p.setStockQuantity(newStockQuantity));
    }

    public void deleteProduct(String productName) {
        electronicsList.removeIf(p -> p.getProductName().equals(productName));
        clothesList.removeIf(p -> p.getProductName().equals(productName));
        foodList.removeIf(p -> p.getProductName().equals(productName));
    }



}
