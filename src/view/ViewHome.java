package view;

import config.Config;
import controller.ProductController;
import controller.UserController;
import dto.response.ResponseMessenger;
import model.RoleName;
import model.Product;
import model.User;

import java.util.ArrayList;
import java.util.List;

import static service.user.UserServiceIMPL.userList;

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
        System.out.println("3. form change password");
        System.out.println("4. Log out");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                new ViewProduct().formShowProductList();
                break;
            case 2:
                new ViewProduct().formDetailProduct();
                break;
            case 3:
                formChangePassword();
                break;
            case 4:
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
        System.out.println("11. form user manage");
        System.out.println("12. form change password");
        System.out.println("13. logout");


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
                formUserManage();
                break;
            case 12:
                formChangePassword();
                break;
            case 13:
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
        System.out.println("11. logout");

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



    public void formUserManage() {
        System.out.println("Menu");
        System.out.println("1. Show list user");
        System.out.println("2. Delete user");
        System.out.println("3. Change role");
        System.out.println("4. Block user");
        System.out.println("5. Change profile");
        System.out.println("6. Back");

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

}
