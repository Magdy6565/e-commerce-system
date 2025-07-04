# E-Commerce System

A Java-based e-commerce system with comprehensive exception handling and testing.

## Features

- Product management with different product types (Cheese, Biscuits, Television, Mobile, MobileScratchCard)
- Shopping cart functionality
- Customer management
- Checkout system with validation
- Shipping service for physical products
- Exception handling for:
  - Empty cart
  - Out of stock products
  - Expired products
  - Insufficient customer balance

## Project Structure

```
src/
├── Main.java                    # Main class with test cases
├── Cart.java                    # Shopping cart implementation
├── Checkout.java                # Checkout process with validations
├── Customer.java                # Customer management
├── ShippingService.java         # Shipping calculations
├── Products/
│   ├── Product.java            # Base product class
│   ├── canExpireProduct.java   # Interface for expirable products
│   ├── cannotExpireProduct.java # Base class for non-expirable products
│   ├── IsShipable.java         # Interface for shippable products
│   ├── Cheese.java             # Expirable dairy product
│   ├── Biscuits.java           # Expirable food product
│   ├── Television.java         # Electronic product
│   ├── Mobile.java             # Electronic product
│   └── MobileScratchCard.java  # Digital product (non-shippable)
└── Exceptions/
    ├── CartEmptyException.java
    ├── OutOfStockException.java
    ├── ExpiredProductException.java
    └── InsufficientBalanceException.java
```

## Test Cases

The Main class includes comprehensive test cases that demonstrate:

1. **Successful Checkout** - Normal flow with no exceptions
2. **Empty Cart Exception** - Attempting to checkout with an empty cart
3. **Out of Stock Exception** - Ordering more items than available
4. **Expired Product Exception** - Attempting to purchase expired products
5. **Insufficient Balance Exception** - Customer doesn't have enough money

## Running the Project

1. Compile the Java files
2. Run the Main class to see all test cases in action

```bash
javac -d out src/**/*.java
java -cp out Main
```

## Author

Developed as part of an e-commerce system learning project.
