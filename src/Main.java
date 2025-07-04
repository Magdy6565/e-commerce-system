import Products.*;
import Exceptions.*;

import java.time.LocalDate;
public class Main {

    public static void main(String[] args) {
        System.out.println("=== E-Commerce System Test Cases ===\n");
        testSuccessfulCheckout();
        testEmptyCartException();
        testOutOfStockException();
        testExpiredProductException();
        testInsufficientBalanceException();
    }
    public static void testSuccessfulCheckout() {
        System.out.println("--- Test Case 1: Successful Checkout ---");
        try {
            Cheese cheese = new Cheese("Cheese", 100, 10, LocalDate.now().plusDays(7), 0.2);
            Biscuits biscuits = new Biscuits("Biscuits", 150, 8, LocalDate.now().plusDays(10), 0.7);
            MobileScratchCard scratchCard = new MobileScratchCard("Mobile Scratch Card", 50, 20);
            Customer customer = new Customer("Youssef Magdu", 1000);
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            Checkout checkout = new Checkout();
            checkout.checkout(customer, cart);
            System.out.println("Customer balance after: $" + customer.getBalance());
            System.out.println("✅ SUCCESS: No exceptions thrown\n");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
        }
    }
    public static void testEmptyCartException() {
        System.out.println("--- Test Case 2: Empty Cart Exception ---");
        try {
            Customer customer = new Customer("Youssef", 500);
            Cart emptyCart = new Cart();
            Checkout checkout = new Checkout();
            checkout.checkout(customer, emptyCart);
            System.out.println("❌ UNEXPECTED: No exception thrown\n");
        } catch (CartEmptyException e) {
            System.out.println("✅ EXPECTED: CartEmptyExc - " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
        }
    }
    public static void testOutOfStockException() {
        System.out.println("--- Test Case 3: Out of Stock Exception ---");
        try {
            Television tv = new Television("TV", 500, 2, 15.0); // Only 2 TVs available
            Customer customer = new Customer("Youssef Magdy", 2000);
            Cart cart = new Cart();
            cart.add(tv, 5); // Try to buy 5 TVs (more than available)
            Checkout checkout = new Checkout();
            checkout.checkout(customer, cart);
            System.out.println("❌ UNEXPECTED: No exception thrown\n");
        } catch (OutOfStockException e) {
            System.out.println("✅ EXPECTED: OutOfStockException - " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
        }
    }

    // Test Case 4: Expired product
    public static void testExpiredProductException() {
        System.out.println("--- Test Case 4: Expired Product Exception ---");

        try {
            Cheese expiredCheese = new Cheese("Expired Cheese", 100, 10, LocalDate.now().minusDays(2), 0.2);
            Customer customer = new Customer("Youssef Magdy", 500);
            Cart cart = new Cart();
            cart.add(expiredCheese, 1);
            Checkout checkout = new Checkout();
            checkout.checkout(customer, cart);

            System.out.println("❌ UNEXPECTED: No exception thrown\n");
        } catch (ExpiredProductException e) {
            System.out.println("✅ EXPECTED: ExpiredProductException - " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
        }
    }

    // Test Case 5: Insufficient balance
    public static void testInsufficientBalanceException() {
        System.out.println("--- Test Case 5: Insufficient Balance Exception ---");

        try {
            Television expensiveTV = new Television("Expensive TV", 1000, 5, 15.0);
            Mobile expensiveMobile = new Mobile("iPhone", 800, 3, 0.2);
            Customer poorCustomer = new Customer("Youssef Magdy Poor", 100); // Only $100
            Cart cart = new Cart();
            cart.add(expensiveTV, 1);    // $1000 + shipping
            cart.add(expensiveMobile, 1); // $800 + shipping
            // Total will be $1800 + shipping, but customer only has $100

            Checkout checkout = new Checkout();
            checkout.checkout(poorCustomer, cart);

            System.out.println("❌ UNEXPECTED: No exception thrown\n");
        } catch (InsufficientBalanceException e) {
            System.out.println("✅ EXPECTED: InsufficientBalanceException - " + e.getMessage() + "\n");
        } catch (Exception e) {
            System.out.println("❌ UNEXPECTED: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "\n");
        }
    }
}