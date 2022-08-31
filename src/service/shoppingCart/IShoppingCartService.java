package service.shoppingCart;

import model.ShoppingCart;
import service.IGenericService;

public interface IShoppingCartService extends IGenericService<ShoppingCart> {
int getLastId();
ShoppingCart findById(int id);

}
