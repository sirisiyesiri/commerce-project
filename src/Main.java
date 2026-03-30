import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommerceSystem commerceSystem = new CommerceSystem();

        while(true) {
            commerceSystem.start();

            int categoryChoice = commerceSystem.inputCategoryChoice();
            if(categoryChoice != 0) {
                if(categoryChoice >= 1 && categoryChoice <= commerceSystem.getCategoryCount()) {
                    commerceSystem.printProductList(categoryChoice);

                    int productChoice = commerceSystem.inputProductChoice(categoryChoice);

                    System.out.println();

                    if(productChoice != 0) {
                        commerceSystem.printChoiceProduct(categoryChoice, productChoice);
                        int addShoppingCart = commerceSystem.inputAddShopping();
                        if(addShoppingCart == 1) {
                            commerceSystem.addShoppingCart(categoryChoice, productChoice);
                        }
                    }
                } else if(categoryChoice == (commerceSystem.getCategoryCount()+1)) {
                    int orderChoice = commerceSystem.inputProductOrder();
                    if(orderChoice == 1) {
                        commerceSystem.orderComplete();
                    }
                } else if(categoryChoice == (commerceSystem.getCategoryCount()+2)) {
                    System.out.println("주문을 취소하였습니다.");
                    System.out.println();
                }
                else {
                    int choiceManagerFunction = commerceSystem.ChoiceManagerFunction();

                    if(choiceManagerFunction != 0) {
                        switch (choiceManagerFunction) {
                            case 1:
                                commerceSystem.addCategoryProduct();
                                break;
                            case 2:
                                commerceSystem.correctProduct();
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            default:
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
