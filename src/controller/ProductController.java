package controller;

import model.Product;
import service.product.IProductService;
import service.product.ProductServiceIMPL;

import java.util.List;

public class ProductController {

    IProductService productService = new ProductServiceIMPL();

    public List<Product> showProductList() {
        return productService.findAll();
    }

    public void createProduct(Product product) {
        productService.save(product);
    }

    public Product detailProduct(int id) {
        return productService.findById(id);
    }

    public void updateProduct(int idProduct, Product product) {
        productService.editById(product);
    }

    public void deleteProduct(int id) {
        productService.deleteById(id);
    }
    public Product findById(int id){
        return productService.findById(id);
    }
    public void sortByPrice(){
        productService.sortByPrice();
    }
    public Product findProductByName(String name) {
        return productService.findProductByName(name);
    }


    public void editProduct1(int id, Product newProduct2) {
        Product product2 = productService.findById(id);
        product2.setAmount(newProduct2.getAmount());
    }
}
