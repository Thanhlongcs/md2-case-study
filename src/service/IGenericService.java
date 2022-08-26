package service;

import model.Product;
import model.ProductCategory;

import java.util.List;

public interface IGenericService<T> {

    List<T> findAll();

    void save(T t);

    void deleteById(int id);

    T findById(int id);
}
