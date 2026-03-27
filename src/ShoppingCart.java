import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Product> shoppingList = new ArrayList<>();
    Category category = new Category();

    ShoppingCart(Category category) {
        this.category = category;
    }

    public void addShoppingCart(int categoryChoice, int productChoice) {
        shoppingList.add(category.getChoiceProductInformation(categoryChoice, productChoice));

        System.out.println(category.getChoiceProductInformation(categoryChoice, productChoice).getProductName() + "가 장바구니에 추가되었습니다.");
        System.out.println();   // 반복 시 사용자 UI를 위한 줄바꿈
    }


}
