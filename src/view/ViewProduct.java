package view;

import config.Config;
import controller.ProductCategoryController;
import controller.ProductController;
import model.Product;
import model.ProductCategory;

import java.util.List;

public class ViewProduct {
    ProductController productController = new ProductController();
    List<Product> productList = productController.showProductList();
    public void formShowProductList() {
//        System.out.println("======id=====name=======price=======description======category=======");
//        for (int i = 0; i < productList.size(); i++) {
//           // int j = i+1;
//            System.out.println("======="+productList.get(i).getId()+"======="+productList.get(i).getName()
//                    +"========"+productList.get(i).getPrice()+"======="+productList.get(i).getDescription()+"======="+productList.get(i).getProductCategory().getName());
//        }
        System.out.println(productController.showProductList());
        System.out.println("input quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewHome();
        }

    }

    public void formCreateProduct() {
        while (true) {
            int id;
            if (productList.isEmpty()) {
                id = 1;
            } else {
                id = productList.get(productList.size() - 1).getId() + 1;
            }
            System.out.println("Enter product name");
            String name = Config.scanner().nextLine();
            System.out.println("input description Product: ");
            String description = Config.scanner().nextLine();
            System.out.println("input price Product: ");
            double price = Config.scanner().nextDouble();
            System.out.println("input id product category");
            int idProductCategory = Config.scanner().nextInt();
            ProductCategory productCategory = new ProductCategoryController().findById(idProductCategory);
            productController.createProduct(new Product(id,name,price,description,productCategory));
            System.out.println("create success");
            System.out.println(productController.showProductList());
            System.out.println("input random key for continue- input Quit for out: ");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewHome();
                break;
            }
        }
    }
    public void formEditProduct() {
        System.out.println("input id for edit product: ");
        int idProduct = Config.scanner().nextInt();
        if(productController.detailProduct(idProduct)==null){
            System.out.println("no exist id product");
        }else{
            Product product = productController.detailProduct(idProduct);
            System.out.println("OLD NAME PRODUCT: "+ product.getName());
            System.out.println("OLD PRICE: "+ product.getPrice());
            System.out.println("OLD DESCRIPTION: "+ product.getDescription());
            System.out.println("OLD CATEGORY: "+ product.getProductCategory());
            System.out.println("input name product for edit: ");
            String newNameProduct = Config.scanner().nextLine();
            System.out.println("input price for edit: ");
            double newPrice = Config.scanner().nextDouble();
            System.out.println("input description for edit: ");
            String newDes = Config.scanner().nextLine();
            System.out.println("input category product for edit: ");
            String newProductCategory = Config.scanner().nextLine();
            ProductCategory productCategory = new ProductCategory(newProductCategory);
            Product newProduct = new Product(idProduct,newNameProduct,newPrice,newDes,productCategory);
            productController.updateProduct(idProduct,newProduct);
            System.out.println("edit success");
            System.out.println(newProduct);
            productController.showProductList();
        }
        System.out.println("input random key for continue- input Quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewHome();
        }
    }
    public void formDetailProduct(){
        System.out.println("input id for show detail: ");
        int idProduct = Config.scanner().nextInt();
        if(productController.detailProduct(idProduct)==null){
            System.out.println("no exist id product");
        }else {
            Product product = productController.detailProduct(idProduct);
            System.out.println(product);
        }
        System.out.println("input quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewHome();
        }
    }
    public void formDeleteProduct(){
        System.out.println("input id for delete:");
        int idProduct = Config.scanner().nextInt();
        if(productController.detailProduct(idProduct)==null){
            System.out.println("id no exist");
        }else{
            System.out.println(" input 1 for delete- input 2 no delete");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete){
                case 1:
                    productController.deleteProduct(idProduct);
                    formShowProductList();
                    productController.showProductList();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }
        }
    }
    public void findProductByName() {
        System.out.println("enter name for search: ");
        String name = Config.scanner().nextLine();
        if (productController.findProductByName(name) != null) {
            System.out.println(productController.findProductByName(name));
        }
       else {
           System.out.println("no exists product");
        }
        System.out.println("Enter random key to continue  or Enter  quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewHome();
        }
    }
    public void findProductById(){
        System.out.println("enter id for search: ");
        int id = Config.scanner().nextInt();
        if(productController.findById(id)!=null){
            System.out.println(productController.findById(id));
        }else {
            System.out.println("no exist product");
        }
        System.out.println("Enter random key to continue  or Enter  quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewHome();
        }
    }
public void sortByPrice(){
    System.out.println("old list");
    System.out.println(productController.showProductList());
    productController.sortByPrice();
    System.out.println("List after sort");
    System.out.println(productController.showProductList());
    System.out.println("Enter random key to continue and enter quit to come back menu: ");
    String backMenu =Config.scanner().nextLine();
    if (backMenu.equalsIgnoreCase("quit")) {
        new ViewHome();
    }


    }

}


