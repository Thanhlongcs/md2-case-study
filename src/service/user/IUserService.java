package service.user;

import model.Role;
import model.User;
import service.IGenericService;

import java.util.List;

public interface IUserService extends IGenericService<User> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean checkLogin(String username, String password);

    User getCurrentUser();

    void saveCurrentUser(List<User> user);

    User findByUsername(String username);

    void remove(int id);

    void changeRole(int id, Role role);

    void changeStatus(int id);
    void changeProfile(User user);

    void updateData();

    int getLastId();
}
