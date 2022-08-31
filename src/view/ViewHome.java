package view;

import config.Config;
import controller.ProductController;
import controller.ShoppingCartController;
import controller.UserController;
import dto.response.ResponseMessenger;
import model.RoleName;
import model.Product;
import model.ShoppingCart;
import model.User;

import java.util.ArrayList;
import java.util.List;

import static service.product.ProductServiceIMPL.productList;
import static service.user.UserServiceIMPL.userList;

public class ViewHome {

    UserController userController = new UserController();

    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();
    ProductController productController = new ProductController();

    List<Product> shoppingCartList = productController.showProductList();
    ShoppingCartController shoppingCartController = new ShoppingCartController();

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
        System.out.println("1. product list");
        System.out.println("2. detail product");
        System.out.println("3. sort by price");
        System.out.println("4. change password");
        System.out.println("5. My Shopping Cart");
        System.out.println("6. Order product ");
        System.out.println("7. Log out");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formShowProductList();
                break;
            case 2:
                new ViewProduct().formDetailProduct();
                break;
            case 3:
                new ViewProduct().sortByPrice();
                break;
            case 4:
                formChangePassword();
                break;
            case 5:
                formMyShoppingCart();
                break;
            case 6:
                formOrderProduct();
            case 7:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuUser();
    }
    private void formMyShoppingCart(){
        System.out.println("MY SHOPPING CART");
        ShoppingCart shoppingCart = shoppingCartController.getMyShoppingCart();
        for (int idProduct : shoppingCart.getShoppingCartMap().keySet()){
            System.out.println("Product name: "+ productController.detailProduct(idProduct).getName()+"\nAmount: " + shoppingCart.getShoppingCartMap().get(idProduct)+"\n");
        }
        System.out.println();
    }
   private void formOrderProduct(){
        while (true){
            System.out.printf("%-5s %-10s %s\n","ID", "NAME","AMOUNT");
            for(Product product: productList){
                System.out.printf("%-5s %-10s %s\n", product.getId(),product.getName(),product.getAmount());
            }
            System.out.println("enter id to add to cart");
            int id = Config.scanner().nextInt();
            if(productController.detailProduct(id).getAmount()!=0){
                Product product = productController.detailProduct(id);
                int newAmount = product.getAmount()-1;
                Product newProduct2 = new Product(newAmount);
                productController.editProduct1(id,newProduct2);
                shoppingCartController.createShoppingCart(id);
                System.out.println("add success");
            }else{
                System.out.println("enter random key to continue- enter quit for out");
                String backMenu = Config.scanner().nextLine();
                if(backMenu.equalsIgnoreCase("quit")){
                    new ViewHome();
                    break;
                }
            }
        }
}
    public void menuAdmin() {
        System.out.println("Hello ADMIN: " + currentUser.getName());
        System.out.println("1. Product manage ");
        System.out.println("2. Product Category manage ");
        System.out.println("3. User manage");
        System.out.println("4. Change Password");
        System.out.println("5. Logout");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formProductManage();
                break;
            case 2:
                new ViewProductCategory().formProductCategoryManage();
                break;
            case 3:
                formUserManage();
                break;
            case 4:
                formChangePassword();
                break;
            case 5:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuAdmin();
    }
    public void menuPM(){
        System.out.println("Hello PM: " + currentUser.getName());
        System.out.println("1. Product manage ");
        System.out.println("2. Product Category manage ");
        System.out.println("3. User manage");
        System.out.println("4. Change Password");
        System.out.println("5. logout");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formProductManage();
                break;
            case 2:
                new ViewProductCategory().formProductCategoryManage();
                break;
            case 3:
                formUserManage();
                break;
            case 4:
                formChangePassword();
                break;
            case 5:
                userController.logout();
                new ViewMainMenu().menu();
                return;
        }
        menuPM();
    }


    public void formUserManage() {
        System.out.println("Menu");
        System.out.println("1. Show list user");
        System.out.println("2. Delete user");
        System.out.println("3. Change role");
        System.out.println("4. Block user");
        System.out.println("5. Change profile");
        System.out.println("6. Back Viewhome");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        if (choice == 0) return;
        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formDeleteUser();
                break;
            case 3:
                formChangeRole();
                break;
            case 4:
                formBlockUser();
                break;
            case 5:
               new ViewChangeProfile().menuProfile();
                return;
            case 6:
                new ViewHome();
            default:
                System.out.println("Invalid choice");
        }

        formUserManage();
    }
    private void formShowListUser() {
        System.out.println("===ID===NAME========USERNAME========EMAIL========ROLE======STATUS=======");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("===" + userList.get(i).getId() + "===" + userList.get(i).getName() + "========"
                    + userList.get(i).getUsername() + "=======" + userList.get(i).getEmail() + "======="
                    + userList.get(i).getRoles()+ "======="+userList.get(i).isStatus());

        }
    }
    private void formDeleteUser() {
        formShowListUser();
        System.out.println("Enter id of user to delete");
        int id = Config.scanner().nextInt();
        ResponseMessenger messenger = userController.deleteUser(id);
        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Delete user successfully");
                break;
            case "not found":
                System.err.println("ID not found");
        }
    }
    private void formChangeRole() {
        formShowListUser();
        System.out.println("Enter id of user to change role");
        int id = Config.scanner().nextInt();
        System.out.println("Enter role to change (pm / user)");
        String roleName = Config.scanner().nextLine();

        ResponseMessenger messenger = userController.changeRole(id, roleName);

        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Change role successfully!");
                break;
            case "invalid role":
                System.err.println("Invalid role!");
                break;
            case "not found":
                System.out.println("ID not found!");
        }
    }
    private void formBlockUser() {
        formShowListUser();
        System.out.println("Enter id user to block");
        int id = Config.scanner().nextInt();
        ResponseMessenger messenger = userController.blockUser(id);

        switch (messenger.getMessage()) {
            case "not found":
                System.err.println("ID not found");
                break;
            case "blocked":
                System.out.println("You just blocked user id " + id);
                break;
            case "unblocked":
                System.out.println("You just unblocked user id " + id);
                break;
            case "jurisdiction":
                System.out.println("khong the han block ADMIN VA PM");
        }
    }
    private void formChangePassword() {
        String oldPassword;
        while (true) {
            System.out.println("Enter your old password:");
            oldPassword = Config.scanner().nextLine();
            if (oldPassword.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }

        System.out.println("Enter your new password: ");
        String newPassword = Config.scanner().nextLine();

        System.out.println("Repeat the new password: ");
        String newPassword2 = Config.scanner().nextLine();

        if (!newPassword.equals(newPassword2)) {
            System.out.println("Repeat password incorrect");
        } else {
            ResponseMessenger messenger = userController.changePassword(oldPassword, newPassword);
            switch (messenger.getMessage()) {
                case "not match":
                    System.out.println("Old password does not matches!");
                    break;
                case "success":
                    System.out.println("Change password successfully!");
                    userController.logout();
                    new ViewMainMenu().menu();
            }
        }

    }


}
