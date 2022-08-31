package service.shoppingCart;

import config.Config;
import model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShoppingCartServiceIMPL implements IShoppingCartService{
    static String PATH_SHOPPING_CART= "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\shoppingcart.txt";
    static List<ShoppingCart> shoppingCartList = new Config<ShoppingCart>().readFile(PATH_SHOPPING_CART);
    static {
        if (shoppingCartList == null) {
            shoppingCartList = new ArrayList<>();
        }
    }
    @Override
    public List<ShoppingCart> findAll() {
        new Config<ShoppingCart>().writeFile(PATH_SHOPPING_CART,shoppingCartList);
        return shoppingCartList;
    }

    @Override
    public void save(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCart1 = findById(shoppingCart.getId());
        if(shoppingCart1==null){
            shoppingCartList.add(shoppingCart);
        }else{
            shoppingCart1.setShoppingCartMap(shoppingCart.getShoppingCartMap());
        }
       new Config<ShoppingCart>().writeFile(PATH_SHOPPING_CART,shoppingCartList);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public int getLastId() {
        return shoppingCartList.isEmpty() ? 1: shoppingCartList.get(shoppingCartList.size()-1).getId()+1;
    }

    @Override
    public ShoppingCart findById(int id) {
        for (ShoppingCart shoppingCart : shoppingCartList){
            if (shoppingCart.getId()==id)
                return shoppingCart;
        }
        return null;
    }
}
