package service.product;

import model.Product;
import service.IGenericService;

import java.util.List;

public interface IProductService extends IGenericService<Product> {


    Product findProductByName(String name);

    void sortByPrice();

    void editById(Product product);
}

