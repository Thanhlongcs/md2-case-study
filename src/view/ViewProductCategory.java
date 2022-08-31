package view;

import config.Config;
import controller.ProductCategoryController;
import controller.ProductController;
import model.Product;
import model.ProductCategory;
import service.ProductCategory.ProductCategoryServiceIMPL;

import java.util.List;


public class ViewProductCategory {
    static ProductCategoryController productCategoryController = new ProductCategoryController();
    ProductController productController = new ProductController ();

    List<Product> productList = productController.showProductList();
    static List<ProductCategory> productCategoryList = ProductCategoryController.showListProductCategory();

public void formProductCategoryManage(){
    System.out.println(" =====PRODUCT MANAGE=====");
    System.out.println("1. Show list product category");
    System.out.println("2. Create product category");
    System.out.println("3. Edit product category");
    System.out.println("4. Delete product category");
    System.out.println("5. back ViewHome");
    int choice = Integer.parseInt(Config.scanner().nextLine());
    switch (choice){
        case 1:
            formShowListProductCategory();
            break;
        case 2:
            formCreateProductCategory();
            break;
        case 3:
            formEditProductCategoryById();
            break;
        case 4:
            formDeleteProductCategory();
            break;
        case 5:
            new ViewHome();
        default:
            System.out.println("invalid choice ");
    }

}
    public void formCreateProductCategory(){
        while (true){
            int id;
            if(productCategoryList.size()==0){
                id=1;
            }else{
                id = productCategoryList.get(productCategoryList.size()-1).getId()+1;
            }
            System.out.println("enter Product name for create:");
            String name = Config.scanner().nextLine();
            ProductCategory productCategory = new ProductCategory(id,name);
            productCategoryController.createProductCategory(productCategory);
            System.out.println(productCategoryController.showListProductCategory());
            System.out.println("enter random for continue and enter quit for out");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                formProductCategoryManage();
            }

        }
    }
    public void formShowListProductCategory() {
        System.out.println("======id=====name=======");
        for (int i = 0; i < productCategoryList.size(); i++) {
            //int j = i + 1;
            System.out.println("=======" + productCategoryList.get(i).getId() + "======="
                            + productCategoryList.get(i).getName()+"=======");
        }
        System.out.println("input quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            formProductCategoryManage();
        }
    }
    public void formDeleteProductCategory(){
        System.out.println("input id for delete:");
        int idProductCategory = Config.scanner().nextInt();
        if(productCategoryController.detailProductCategory(idProductCategory)==null){
            System.out.println("id no exist");
        }else{
            System.out.println(" input 1 for delete- input 2 no delete");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete){
                case 1:
                    productCategoryController.deleteById(idProductCategory);
                    formShowListProductCategory();
                    productCategoryController.showListProductCategory();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }
        }
    }
    public void formDetailProductCategory(){
        System.out.println("input id for show detail: ");
        int idProductCategory = Config.scanner().nextInt();
        if(productCategoryController.detailProductCategory(idProductCategory)==null){
            System.out.println("no exist id product category");
        }else {
            ProductCategory productCategory = productCategoryController.detailProductCategory(idProductCategory);
            System.out.println(productCategory);
        }
        System.out.println("input quit for out: ");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            formProductCategoryManage();
        }
    }
    public void formEditProductCategoryById(){
        while (true) {
            System.out.println(productCategoryController.showListProductCategory());
            System.out.println("Nhap ID muon sua:");
            int id =Config.scanner().nextInt();
            System.out.println("Enter the name ProductCategory: ");
            String name =Config.scanner().nextLine();
            ProductCategory productCategory = new ProductCategory(id, name);
            productCategoryController.editById(productCategory);
            System.out.println(productCategoryController.showListProductCategory());
            System.out.println("Enter random key to continue edit  and Enter quit for out: ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                formProductCategoryManage();
            }
        }
    }
}