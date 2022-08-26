package service.ProductCategory;

import config.Config;
import model.Product;
import model.ProductCategory;
import service.product.ProductServiceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static service.product.ProductServiceIMPL.productList;

public class ProductCategoryServiceIMPL implements IProductCategoryService {
    public static String PATH_CATEGORY = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\productCategory.txt";
    public static List<ProductCategory> productCategoryList = new Config<ProductCategory>().readFile(PATH_CATEGORY);

    @Override
    public List<ProductCategory> findAll() {
        new Config<ProductCategory>().writeFile(PATH_CATEGORY,productCategoryList);
        return productCategoryList;
    }

    @Override
    public void save(ProductCategory productCategory) {
        productCategoryList.add(productCategory);

    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < productCategoryList.size(); i++) {
            if (id == productCategoryList.get(i).getId()) {
                productCategoryList.remove(productCategoryList.get(i)) ;

            }

        }
    }
    @Override
    public ProductCategory findById(int id) {
        for (int i = 0; i < productCategoryList.size(); i++) {
            if(id==productCategoryList.get(i).getId()){
                return productCategoryList.get(i);
            }
        }
        return null;
    }
    public List<Product> findByNameCategory(String name) {
    List<Product> productList=new ArrayList<>();
        for (int i = 0; i < new ProductServiceIMPL().findAll().size(); i++) {
        if (name.equals(new ProductServiceIMPL().findAll().get(i).getProductCategory().getName())){

            productList.add(new ProductServiceIMPL().findAll().get(i));
        }
    }
        return productList;
}
    public void editById(ProductCategory productCategory) {
        for (int i = 0; i < productCategoryList.size(); i++) {
            if (productCategory.getId()==productCategoryList.get(i).getId()){
                productCategoryList.get(i).setName(productCategory.getName());
            }
        }
    }

}

