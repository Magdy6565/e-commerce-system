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

## Test Cases

The Main class includes comprehensive test cases that demonstrate:

1. **Successful Checkout** - Normal flow with no exceptions
2. **Empty Cart Exception** - Attempting to checkout with an empty cart
3. **Out of Stock Exception** - Ordering more items than available
4. **Expired Product Exception** - Attempting to purchase expired products
5. **Insufficient Balance Exception** - Customer doesn't have enough money

