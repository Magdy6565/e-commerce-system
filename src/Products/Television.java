package Products;

public class Television extends cannotExpireProduct implements IsShipable{
    private double weight;
    public Television(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight =weight ;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
}

