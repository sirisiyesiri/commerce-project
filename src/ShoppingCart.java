import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {
    ArrayList<Product> shoppingList = new ArrayList<>();

    public void addShoppingCart(Product product) {
        shoppingList.add(product);

        System.out.println(product.getProductName() + "가 장바구니에 추가되었습니다.");
        System.out.println();   // 반복 시 사용자 UI를 위한 줄바꿈
    }

    public boolean notShoppingCatrEmpty() {
        if(!shoppingList.isEmpty()) return true;
        else {
            return false;
        }
    }

    public ArrayList<Product> getShoppingList() {
        return shoppingList;
    }

    public String totalPrice() {
        int total = shoppingList.stream()
                .mapToInt(product -> Integer.parseInt(product.getPrice().replaceAll("[^0-9]", ""))*product.getCartCount()).sum();
        // replaceAll("[^0-9]",""); : 숫자(0~9)가 아닌 모든 문자를 찾아서 제거한다.

        String strTotal = String.format("%,d", total);
        // ",d" 정수를 1000단위로 콤마를 추가한다.
        return strTotal;

    }

    public void EditStockQuantity(int index, int count) {
        shoppingList.get(index).reduceStockQuantity(count);
    }

    public void resetShoppingList() {
        shoppingList.clear();
    }

    public void deleteShoppingProduct(String productName) {
        shoppingList.removeIf(p -> p.getProductName().equals(productName));
    }

    public Product searchProduct(String productName) {
        return shoppingList.stream()
                .filter(product -> product.getProductName().equals(productName))
                .findFirst()
                .orElse(null);
    }
}
