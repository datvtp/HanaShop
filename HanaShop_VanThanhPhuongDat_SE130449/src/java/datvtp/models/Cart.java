package datvtp.models;

import datvtp.dtos.Tbl_FoodDTO;
import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {

    private String username;
    private HashMap<Integer, Tbl_FoodDTO> shoppingCart;

    public Cart(String username) {
        this.username = username;
        this.shoppingCart = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<Integer, Tbl_FoodDTO> getShoppingCart() {
        return shoppingCart;
    }

    public void addToCart(Tbl_FoodDTO dto) throws Exception {
        if (this.shoppingCart.containsKey(dto.getFoodId())) {
            int quantity = this.shoppingCart.get(dto.getFoodId()).getQuantity() + dto.getQuantity();
            dto.setQuantity(quantity);
        }
        this.shoppingCart.put(dto.getFoodId(), dto);
    }

    public void updateCart(int foodId, int quantity) throws Exception {
        if (this.shoppingCart.containsKey(foodId)) {
            this.shoppingCart.get(foodId).setQuantity(quantity);
        }
    }

    public void removeCart(int foodId) throws Exception {
        if (this.shoppingCart.containsKey(foodId)) {
            this.shoppingCart.remove(foodId);
        }
    }

    public int getTotal() throws Exception {
        int total = 0;
        for (Tbl_FoodDTO dto : this.shoppingCart.values()) {
            total += (dto.getPrice() * dto.getQuantity());
        }
        return total;
    }

}
