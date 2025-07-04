import Exceptions.CartEmptyException;
import Exceptions.*;
import Products.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* I will make all the logic heeereee
* 1) get the cart and customer  do the following checks :: I will take them as params easier for me
* a- check if all cart.items  has quantity <= item.quantity(I already made this in cart.add prod but to make 100% sure)
* b- check if any item is CanExpire --> is it expried --> return error
* c - check if is cart empty or not (I made func in cart to return bool)
* d - Get total and then check customer balance if he can pay
* e- if he can pay then decrement his balance automatically
* */
public class Checkout {
    private ShippingService shippingService;
    public Checkout(){
        this.shippingService = new ShippingService();
    }
    public void checkout(Customer customer , Cart cart) throws CartEmptyException, OutOfStockException, ExpiredProductException, InsufficientBalanceException {
//        try {
            double oldBalance  = customer.getBalance();
            //1- check if the given cart is empty
            if (cart.isCartEmpty()) {
//                System.out.println("Hello inside empty cart");
                throw new CartEmptyException("ERROR !! CART IS EMPTY");
            }
//            System.out.println("Hello outside empty cart");
            // -------------------------------------------------
            for (Map.Entry<Product, Integer> entry : cart.getCartneo().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                //2- check if quantity ordered > quantity exceeded
                if (product.getQuantity() < quantity) {
                    throw new OutOfStockException("Product " + product.getName() + " is out of stock. Available: " + product.getQuantity() + ", Requested: " + quantity);
                }
                //3-check if product is expired
                // to make this we need to check if the product is expirable ar first
                if (product instanceof canExpireProduct) {
                    // is it expired or not ?
                    if (((canExpireProduct) product).isExpiredProduct()) {
                        throw new ExpiredProductException("ERROR!! PRODUCT IS EXPIRED");
                    }
                }

            }
            // -------------------------------------------------
            // The only remaining validation is only the balance of userrr
            double productsPrice = cart.showPrice();
            // I also need to get the shipping price here !
            double totalShippingPrice = getShippingPrice(cart);
            double totalPrice = productsPrice + totalShippingPrice;
            if (customer.getBalance() < totalPrice) {
                throw new InsufficientBalanceException("ERROR !! Customer dont have enough balance");
            }
            // If we reached this point then the customer balance is enough
            double newBalance = customer.getBalance() - totalPrice;
            customer.setBalance(newBalance);
            // --------------
            // wee need to update the item quantity counts currently
            for (Map.Entry<Product, Integer> entry : cart.getCartneo().entrySet()) {
                int newquant = entry.getKey().getQuantity() - entry.getValue();
                entry.getKey().setQuantity(newquant);
            }
            // I already Hnadled shipping the items in the get shipping price
            // ---------------------------------
            // Now i will print the receipt
            System.out.println("** Checkout receipt **");
            for (Map.Entry<Product, Integer> entry : cart.getCartneo().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(quantity + "x " + product.getName() + " " + (int)(product.getPrice() * quantity));
            }
            System.out.println("----------------------");
            System.out.println("Subtotal " + (int)productsPrice);
            System.out.println("Shipping " + (int)totalShippingPrice);
            System.out.println("Amount " + (int)totalPrice);
        System.out.println("Customer Old balance $ " + (double) oldBalance);
//        System.out.println("Customer currnet Balance" + (int)customer.getBalance());
//        }
//        catch(Exception e) {
//            System.err.println("Checkout failed: " + e.getMessage());
//        }

    }
    public double getShippingPrice(Cart cart){
        // collect all items that need to be shipped and send them to ShippingService
        List<IsShipable> shippedItems = new ArrayList<>();
        // Loop over all prods in cart and if ele is instace from isShip. interface send to shipservoce
        // In problem statment it is stated that we should send a !!!! list of objects !! so we need here
        // if we have cheese with quantity X3 then insert in list [cheese,cheese,cheese]
        for (Map.Entry<Product, Integer> entry : cart.getCartneo().entrySet()) {
            if (entry.getKey() instanceof IsShipable) {
                for (int i = 0; i < entry.getValue(); i++) {
                    shippedItems.add((IsShipable) entry.getKey());
                }
            }
        }
        // I will send all this list to Shiiping service i need 2 things from it
        //1- total weight of shipped items + total price
        double[] result  = shippingService.shipItems(shippedItems);
        return result[1];
    }
}
