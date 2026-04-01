import java.util.*;

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
        for(Product product : electronicsList) {
            if(product.getProductName().equals(searchProductName)) {
                return product;
            }
        }
        for (Product product : clothesList) {
            if(product.getProductName().equals(searchProductName)) {
                return product;
            }
        }
        for(Product product : foodList) {
            if(product.getProductName().equals(searchProductName)) {
                return product;
            }
        }
        return null;
    }

    public void resettingProductPrice(Product product, String newPrice) {
        for(Product imsi : electronicsList) {
            if(product == imsi) {
                imsi.setPrice(newPrice);
                return;
            }
        }
        for(Product imsi : clothesList) {
            if(product == imsi) {
                imsi.setPrice(newPrice);
                return;
            }
        }
        for(Product imsi : foodList) {
            if(product == imsi) {
                imsi.setPrice(newPrice);
                return;
            }
        }
    }

    public void resettingProductDescription(Product product, String newDescription) {
        for (Product imsi : electronicsList) {
            if (product == imsi) {
                imsi.setDescription(newDescription);
                return;
            }
        }
        for (Product imsi : clothesList) {
            if (product == imsi) {
                imsi.setDescription(newDescription);
                return;
            }
        }
        for (Product imsi : foodList) {
            if (product == imsi) {
                imsi.setDescription(newDescription);
                return;
            }
        }
    }

    public void resettingProductStockQuantity(Product product, int newStockQuantity) {
        for (Product imsi : electronicsList) {
            if (product == imsi) {
                imsi.setStockQuantity(newStockQuantity);
                return;
            }
        }
        for (Product imsi : clothesList) {
            if (product == imsi) {
                imsi.setStockQuantity(newStockQuantity);
                return;
            }
        }
        for (Product imsi : foodList) {
            if (product == imsi) {
                imsi.setStockQuantity(newStockQuantity);
                return;
            }
        }
    }

    public void deleteProduct(String productName) {
        electronicsList.removeIf(p -> p.getProductName().equals(productName));
        clothesList.removeIf(p -> p.getProductName().equals(productName));
        foodList.removeIf(p -> p.getProductName().equals(productName));
    }



}
