public class Product {
    private String productName; // 상품명
    private String price;  // 가격
    private String description; // 설명
    private int stockQuantity;  // 재고수량

    Product(String productName, String price, String description, int stockQuantity) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

}
