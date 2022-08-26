package service.user;

import config.Config;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {

    static String PATH_USER = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\user.txt";
    static String PATH_USER_LOGIN = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\user_login.txt";

    static List<User> userList = new Config<User>().readFile(PATH_USER);

    static {
        if (userList == null) {
            userList = new ArrayList<>();
        }
    }


    @Override
    public List<User> findAll() {
        new Config<User>().writeFile(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);

    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (id == userList.get(i).getId()) {
                userList.remove(i);
            }
        }
    }


    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    @Override
    public boolean existsByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        User user;
        if (new Config<User>().readFile(PATH_USER_LOGIN) != null) {
            if(new Config<User>().readFile(PATH_USER_LOGIN).size() != 0){
                user = new Config<User>().readFile(PATH_USER_LOGIN).get(0);
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().writeFile(PATH_USER_LOGIN, userList);
        userList.add(user);
    }

    @Override
    public User findByUsername(String username) {
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) ;
            {
                return userList.get(i);
            }
        }
        return null;
    }
}
