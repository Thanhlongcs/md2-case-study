package view;

import controller.UserController;
import model.User;
public class Main {
    UserController userController = new UserController();

    public Main() {
        User currentUser = userController.getCurrentUser();
        if (currentUser == null) {
            new ViewMainMenu().menu();
        } else {
            new ViewHome();
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}
