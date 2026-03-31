import java.util.ArrayList;

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
        int total = 0;
        for(Product product : shoppingList) {
            total += Integer.parseInt(product.getPrice().replaceAll("[^0-9]", ""))*product.getCartCount();
            // replaceAll("[^0-9]",""); : 숫자(0~9)가 아닌 모든 문자를 찾아서 제거한다.
        }

        String strTotal = String.format("%,d", total);
        // ",d" 정수를 1000단위로 콤마를 추가한다.
        return strTotal;


        // 만약 total이 1200000이면 1,200,000 이런 식으로 문자열로 반환할 수 있게 만들어야 할 것 같음.
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

}
