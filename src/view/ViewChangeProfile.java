package view;

import config.Config;
import controller.UserController;
import model.User;
import service.user.IUserService;
import service.user.UserServiceIMPL;
import java.util.List;


public class ViewChangeProfile {
    IUserService userService = new UserServiceIMPL();
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    User currentUser = userController.getCurrentUser();

    public void menuProfile() {
        System.out.println("====change profile======");
        System.out.println("hello: " + currentUser.getName() + ":" + currentUser.getEmail());
        System.out.println("1. change name");
        System.out.println("2. change email");
        System.out.println("3. Out");
        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formChangeName();
                break;
            case 2:
                formChangeEmail();
                break;
            default:
                new ViewHome().formUserManage();
        }
    }

    private void formChangeEmail() {
        User user = userController.getCurrentUser();
        String email;
        while (true) {
            System.out.println("enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")) {
                break;
            } else {
                System.out.println("invalid email, try again");
            }
        }
        User user1 = new User(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        if (userController.existByEmail(email)) {
            System.out.println("email existed");
        } else {
            userController.updateProfile(user1);
            System.out.println("change email success");
        }
        System.out.println(userController.getUserList());
        menuProfile();
    }
    private void formChangeName(){
        User user = userController.getCurrentUser();
        String name;
        while (true){
            System.out.println("Enter the name");
            name = Config.scanner().nextLine();
            if(name.matches("[A-Z][a-zA-Z0-9]{1,10}")){
                break;
            }else {
                System.out.println("change name failed, try again");
            }
        }
        User user1 = new User(user.getId(),user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
        userController.updateProfile(user1);
        System.out.println("change name success");
        System.out.println(userController.getUserList());
        menuProfile();
    }

}