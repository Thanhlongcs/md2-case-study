package service.user;

import config.Config;
import model.Role;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {

    static String PATH_USER = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\user.txt";
    static String PATH_USER_LOGIN = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\user_login.txt";

    public static List<User> userList = new Config<User>().readFile(PATH_USER);

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
        new Config<User>().writeFile(PATH_USER, userList);
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
        System.out.println(username);
        System.out.println(password);
        for (User user : userList) {
            System.out.println(user);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
//        User user;
//        if (new Config<User>().readFile(PATH_USER_LOGIN) != null) {
//            if(new Config<User>().readFile(PATH_USER_LOGIN).size() != 0){
//                user = new Config<User>().readFile(PATH_USER_LOGIN).get(0);
//                return user;
//            }
//        }
//        return null;
//    }
        User user = new  Config < User > ().readFile(PATH_USER_LOGIN).get(0);
        if (user==null) return null;
        return  findByUsername(user.getUsername());
    }
    @Override
    public void saveCurrentUser(User user) {
        new Config<User>().writeFile(PATH_USER_LOGIN, userList);
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

    @Override
    public void remove(int id) {
        userList.remove(findById(id));
        new Config<User>().writeFile(PATH_USER, userList);
    }

    @Override
    public void changeRole(int id, Role role) {
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        findById(id).setRoles(roles);
        updateData();
    }

    @Override
    public void changeStatus(int id) {
        User user = findById(id);
        user.setStatus(!user.isStatus());
        updateData();
    }

    @Override
    public void changeProfile(User user) {
       User user1 = findByUsername(user.getUsername());
       user1.setName(user.getName());
       user1.setUsername(user.getUsername());
       user1.setEmail(user.getEmail());
       user1.setPassword(user.getPassword());
        new Config<User>().writeFile(PATH_USER, userList);
    }


    @Override
    public void updateData() {
        new Config<User>().writeFile(PATH_USER, userList);
    }

    @Override
    public int getLastId() {
        return userList.get(userList.size() - 1).getId() + 1;
    }
}
