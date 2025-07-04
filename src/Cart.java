import Exceptions.OutOfStockException;
import Products.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product , Integer> cartneo ;
    public Cart() {
        this.cartneo = new HashMap<>();
    }

    public void add(Product product, int quantity) throws OutOfStockException {
        // first of all we need to sum up quantity of this prod already in cart and the new quantity
        // that the user request  then we have to check if all this is <= the Prod quantity
        // Finally if it is possible we will add it to the Cart (Hashmaap✔)
        // --------------------------------------------------
        int currentCartQuantity = this.cartneo.getOrDefault(product, 0);
        int totalRequested = currentCartQuantity + quantity;
        if (product.getQuantity() >= totalRequested) {
            this.cartneo.put(product, totalRequested);
        } else {
            throw new OutOfStockException("Insufficient stock for product: " + product.getName() +
                    ". Available: " + product.getQuantity() + ",Total Requested: " + totalRequested);
        }
    }

    public void removeProduct(Product prod){
        this.cartneo.remove(prod);
    }
    //I will need this to return error if customer required to checkout and cart is empty
    public boolean isCartEmpty(){
        return this.cartneo.isEmpty();
    }
    public double showPrice(){
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : this.cartneo.entrySet()) {
            // getPrice * QUantity of 1 element ..
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<Product, Integer> getCartneo() {
        return cartneo;
    }

    public void setCartneo(Map<Product, Integer> cartneo) {
        this.cartneo = cartneo;
    }
}
