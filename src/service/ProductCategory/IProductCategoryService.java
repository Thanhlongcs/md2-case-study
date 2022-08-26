package service.ProductCategory;

import model.Product;
import model.ProductCategory;
import service.IGenericService;

import java.util.List;


public interface IProductCategoryService extends IGenericService<ProductCategory> {

    List<Product> findByNameCategory(String name);

    void editById(ProductCategory productCategory);

}
