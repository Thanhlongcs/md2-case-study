package controller;

import model.Product;
import model.ProductCategory;
import service.ProductCategory.IProductCategoryService;
import service.ProductCategory.ProductCategoryServiceIMPL;

import java.util.List;

public class ProductCategoryController {
   static IProductCategoryService productCategoryService = new ProductCategoryServiceIMPL();

    public static List<ProductCategory> showListProductCategory(){
        return productCategoryService.findAll();
    }
    public List<Product> findProductByName(String name){
        return productCategoryService.findByNameCategory(name);
    }
    public void createProductCategory(ProductCategory productCategory){
        this.productCategoryService.save(productCategory);
    }
    public  ProductCategory findById(int id){
        return productCategoryService.findById(id);
    }
    public  ProductCategory detailProductCategory(int id){
        return productCategoryService.findById(id);
    }
    public void deleteById(int id){
        productCategoryService.deleteById(id);
    }
    public void editById(ProductCategory productCategory){
        productCategoryService.editById(productCategory);
    }

}
