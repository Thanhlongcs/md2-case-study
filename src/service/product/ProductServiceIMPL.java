package service.product;

import config.Config;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductServiceIMPL implements IProductService {

   public static String PATH_PRODUCT = "C:\\Users\\THINKPAD\\Downloads\\MD2-case-study\\src\\database\\product.txt";
   public static List<Product> productList =  new Config<Product>().readFile(PATH_PRODUCT);


    @Override
    public List<Product> findAll() {
        new Config<Product>().writeFile(PATH_PRODUCT,productList);
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
        new Config<Product>().writeFile(PATH_PRODUCT,productList);
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size() ; i++) {
            if(id == productList.get(i).getId()){
                return productList.get(i);
            }
        }
        return null;
    }
    public Product findProductByName(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if (name.equals(productList.get(i).getName())) {
                System.out.println(productList.get(i));
            }
        }
        return null;
    }

    @Override
    public void sortByPrice() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) {
                    return -1;
                }
                return 1;
            }
        });
    }
    public void editById(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (product.getId() == productList.get(i).getId()) {
                productList.get(i).setName(product.getName());
                productList.get(i).setPrice(product.getPrice());
                productList.get(i).setDescription(product.getDescription());
                productList.get(i).setProductCategory(product.getProductCategory());
            }
        }
        new Config<Product>().writeFile(PATH_PRODUCT,productList);
    }

    public void deleteById(int id){
        for (int i = 0; i < productList.size(); i++) {
            if(id== productList.get(i).getId()){
                productList.remove(i);
            }
        }
        new Config<Product>().writeFile(PATH_PRODUCT,productList);
    }
}
