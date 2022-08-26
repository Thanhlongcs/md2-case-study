package view;

import config.Config;
import controller.UserController;
import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViewMainMenu {

    UserController userController = new UserController();

    List<User> userList = userController.getUserList();

    public void menu() {
        System.out.println("*****MENU*****");
        System.out.println("1. Show user list");
        System.out.println("2. Register");
        System.out.println("3 .Login");

        int choice = Integer.parseInt(Config.scanner().nextLine());

        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formRegister();
                break;
            case 3:
                formLogin();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menu();
    }

    private void formLogin() {
        String username;
        while (true) {
            System.out.println("Enter username:");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }
        //password
        String password;
        while (true) {
            System.out.println("Enter password:");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        SignInDTO signInDTO = new SignInDTO(username, password);

        ResponseMessenger responseMessenger = userController.login(signInDTO);

        switch (responseMessenger.getMessenger()) {
            case "login_success":
                System.out.println("Login successful!");
                new ViewHome();
                break;
            case "login_failure":
                System.out.println("Username or password is incorrect!");
                break;
        }

    }

    private void formRegister() {
        System.out.println("*****REGISTER*****");
        //id
        int id;
        if (userList.isEmpty()) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        //name
        String name;
        while (true) {
            System.out.println("Enter name:");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z ]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid name, try again!");
            }
        }
        //username
        String username;
        while (true) {
            System.out.println("Enter username:");
            username = Config.scanner().nextLine();
            if (username.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid username, try again!");
            }
        }

        String email;
        while (true) {
            System.out.println("Enter email:");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.out.println("Invalid email, try again!");
            }
        }

        String password;
        while (true) {
            System.out.println("Enter password:");
            password = Config.scanner().nextLine();
            if (password.matches("[a-zA-Z0-9]{1,10}")) {
                break;
            } else {
                System.out.println("Invalid password, try again!");
            }
        }
        System.out.println("Enter role:");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);

        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRole);

        ResponseMessenger responseMessenger = userController.register(signUpDTO);

        switch (responseMessenger.getMessenger()) {
            case "user_existed":
                System.out.println("Username already exists");
                break;
            case "email_existed":
                System.out.println("Email already exists");
                break;
            case "invalid_role":
                System.out.println("Invalid role, try again!");
                break;
            case "success":
                System.out.println("Register success");
        }
    }

    private void formShowListUser() {
        System.out.println("===ID===NAME========USERNAME========EMAIL========ROLE======");
        for (int i = 0; i < userList.size(); i++) {
            System.out.println("===" + userList.get(i).getId() + "===" + userList.get(i).getName() + "========"
                    + userList.get(i).getUsername() + "=======" + userList.get(i).getEmail() + "======="
                    + userList.get(i).getRoles() + "=======");

        }
    }
}