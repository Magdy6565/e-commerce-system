package Products;
import java.time.LocalDate;

public class canExpireProduct extends  Product{
    private LocalDate expiryDate ;

    public canExpireProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    // I want to make a helper fun here to return T/F if ele is exp
    public boolean isExpiredProduct(){
        return LocalDate.now().isAfter(this.expiryDate);
    }
}
