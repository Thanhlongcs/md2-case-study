package view;

import config.Config;
import controller.ProductController;
import controller.UserController;
import model.RoleName;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewHome {

    UserController userController = new UserController();

    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();

    public ViewHome() {
        switch (roleName) {
            case ADMIN:
                menuAdmin();
                break;
            case PM:
                menuPM();
                break;
            case USER:
                menuUser();
        }
    }


    public void menuUser() {
        System.out.println("Hello USER: " + currentUser.getName());
        System.out.println("1. show product list");
        System.out.println("2. show detail product");
        System.out.println(". Log out");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formShowProductList();
                break;
            case 2:
                new ViewProduct().formDetailProduct();
                break;
            case 3:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuUser();
    }

    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Show product list");
        System.out.println("2. Create product");
        System.out.println("3. edit product");
        System.out.println("4. delete product");
        System.out.println("5. search product");
        System.out.println("6. detail product");
        System.out.println("7. show list product category");
        System.out.println("8. create product category");
        System.out.println("9. edit product category");
        System.out.println("10. delete product category");
        System.out.println("7. logout");


        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formShowProductList();
                break;
            case 2:
                new ViewProduct().formCreateProduct();
                break;
            case 3:
                new ViewProduct().formEditProduct();
                break;
            case 4:
                new ViewProduct().formDeleteProduct();
                break;
            case 5:
                new ViewProduct().findProductById();
                break;
            case 6 :
                new ViewProduct().formDetailProduct();
                break;
            case 7:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuAdmin();
    }
    public void menuPM(){
        System.out.println("Hello PM: " + currentUser.getName());
        System.out.println("1. Show product list");
        System.out.println("2. Create product");
        System.out.println("3. edit product");
        System.out.println("4. delete product");
        System.out.println("5. search product");
        System.out.println("6. detail product");
        System.out.println("7. show list product category");
        System.out.println("8. create product category");
        System.out.println("9. edit product category");
        System.out.println("10. delete product category");
        System.out.println("7. logout");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formShowProductList();
                break;
            case 2:
                new ViewProduct().formCreateProduct();
                break;
            case 3:
                new ViewProduct().formEditProduct();
                break;
            case 4:
                new ViewProduct().formDeleteProduct();
                break;
            case 5:
                new ViewProduct().findProductById();
                break;
            case 6 :
                new ViewProduct().formDetailProduct();
                break;
            case 7:
                new ViewProductCategory().formShowListProductCategory();
                break;
            case 8:
                new ViewProductCategory().formCreateProductCategory();
                break;
            case 9:
                new ViewProductCategory().formEditProductCategoryById();
                break;
            case 10:
                new ViewProductCategory().formDeleteProductCategory();
                break;
            case 11:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuPM();
    }


}
