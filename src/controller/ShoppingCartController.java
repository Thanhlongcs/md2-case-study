package controller;

import model.Product;
import model.ShoppingCart;
import model.User;
import service.product.ProductServiceIMPL;
import service.shoppingCart.IShoppingCartService;
import service.shoppingCart.ShoppingCartServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.stream.Collectors;

public class ShoppingCartController {
    IShoppingCartService shoppingCartServiceIMPL = new ShoppingCartServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    public void createShoppingCart(int idProduct){
        ShoppingCart shoppingCart = getMyShoppingCart();
        if(shoppingCart==null){
            shoppingCart = new ShoppingCart(shoppingCartServiceIMPL.getLastId(),currentUser.getId());
        }
        if (!new ProductServiceIMPL().findAll().stream().map(Product::getId).collect(Collectors.toList()).contains(idProduct)) {
            System.out.println("create err!");
            return;
        }
        shoppingCart.addToCart(idProduct);
        shoppingCartServiceIMPL.save(shoppingCart);
    }

    public ShoppingCart getMyShoppingCart(){
        return shoppingCartServiceIMPL.findAll().stream().filter(o->o.getIdUser()==currentUser.getId()).findAny().orElse(null);
    }

}
