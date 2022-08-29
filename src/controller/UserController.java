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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {



    public boolean existByEmail(String email){
        return userService.existsByEmail(email);
    };
    IUserService userService = new UserServiceIMPL();

    IRoleService roleService = new RoleServiceIMPL();
    User currentUser = userService.getCurrentUser();

    public List<User> getUserList() {
       return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("user existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email existed");
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
                case "pm":
                    roles.add(roleService.findByRoleName(RoleName.PM));
                    break;
                default:
                    return new ResponseMessenger("invalid role");
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
        getUserList();
        return new ResponseMessenger("success");
    }
    public int getLastId() {
        return userService.getLastId();
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessenger("login failure");
        }

        User userLogin = userService.findByUsername(signInDTO.getUsername());

        userService.saveCurrentUser(userLogin);

        return new ResponseMessenger("login success");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public ResponseMessenger deleteUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not found");
        }
        userService.remove(id);
        return new ResponseMessenger("success");
    }
    public void logout() {
        userService.saveCurrentUser(null);
    }

    public ResponseMessenger changeRole(int id, String roleName) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not found");
        }
        if (!roleName.equals("user") && !roleName.equals("pm")) {
            return new ResponseMessenger("invalid role");
        }
        Role role = roleName.equals("user") ? roleService.findByRoleName(RoleName.USER) : roleService.findByRoleName(RoleName.PM);
        userService.changeRole(id, role);
        return new ResponseMessenger("success");

    }

    public ResponseMessenger blockUser(int id) {
        User blockUser = userService.findById(id);
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not found");
        }
        Role role = new ArrayList<>(getCurrentUser().getRoles()).get(0);
        Role roleBlock = new ArrayList<>(blockUser.getRoles()).get(0);
        if (role.getRoleName() == RoleName.PM && (roleBlock.getRoleName() == RoleName.PM || roleBlock.getRoleName() == RoleName.ADMIN)) {
            return new ResponseMessenger("jurisdiction");
        }
        userService.changeStatus(id);
        boolean check = userService.findById(id).isStatus();
        if (check) {
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }
    }

    public ResponseMessenger changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(currentUser.getPassword())) {
            return new ResponseMessenger("not match");
        }
        currentUser.setPassword(newPassword);
        userService.updateData();
        return new ResponseMessenger("success");
    }
    public void changeStatus(int id){
        userService.changeStatus(id);
    }
    public void updateProfile(User user){
        userService.changeProfile(user);
    }

    }

