public class Customer {
    private String name ;
    private double balance ;
    // customer must have balance and we should check if he can pay the amount of money
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

}
