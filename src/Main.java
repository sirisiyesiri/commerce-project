import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Category category = new Category();
        CommerceSystem commerceSystem = new CommerceSystem(category);

        while(true) {
            commerceSystem.start();

            int categoryChoice = commerceSystem.inputCategoryChoice();
            if(categoryChoice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                return;
            }

            category.printProduct(categoryChoice);

            int productChoice = commerceSystem.inputProductChoice(categoryChoice);

            System.out.println();

            if(productChoice == 0) continue;
            category.printChoiceProduct(categoryChoice, productChoice);
        }
    }
}
