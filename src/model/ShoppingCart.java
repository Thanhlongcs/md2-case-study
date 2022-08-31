package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {
    private int id;
    private int idUser;
    private Map<Integer, Integer> shoppingCartMap = new HashMap<>();

    private boolean status;

    public ShoppingCart() {
    }

    public ShoppingCart(int id, int idUser) {
        this.id = id;
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getShoppingCartMap() {
        return shoppingCartMap;
    }

    public void setShoppingCartMap(Map<Integer, Integer> cartMap) {
        this.shoppingCartMap = shoppingCartMap;
    }

    public void addToCart(int idCart) {
        if (shoppingCartMap.containsKey(idCart)) {
            shoppingCartMap.put(idCart, shoppingCartMap.get(idCart) + 1);
        } else {
            shoppingCartMap.put(idCart, 1);
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", cartMap=" + shoppingCartMap +
                ", status=" + status +
                '}';
    }

}
