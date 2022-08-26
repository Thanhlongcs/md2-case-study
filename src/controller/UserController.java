package controller;

import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.Role;
import model.RoleName;
import model.User;
import service.role.IRoleService;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    IUserService userService = new UserServiceIMPL();

    IRoleService roleService = new RoleServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("user_existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();

        for (String role : strRole) {
            switch (role) {
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("invalid_role");
            }
        }

        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles
        );

        userService.save(user);

        return new ResponseMessenger("success");
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessenger("login_failure");
        }

        User userLogin = userService.findByUsername(signInDTO.getUsername());

        userService.saveCurrentUser(userLogin);

        return new ResponseMessenger("login_success");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public void logout() {
        userService.saveCurrentUser(null);
    }
}
