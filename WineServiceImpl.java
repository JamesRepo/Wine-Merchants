import java.math.BigDecimal;

public class WineServiceImpl implements WineService {

    // Constant final variable to represent the percentage of service charge
    private final double SERVICE = 0.2;

    public WineServiceImpl() {}

    /**
     * Methods for performing operations below
     */
    // To process a sale
    @Override
    public BigDecimal processSale(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    // To process a return
    @Override
    public BigDecimal processReturn(BigDecimal price, int quantity) {
        // Calculate amount to take off return as service charge - There is a service charge of 20% on returns
        BigDecimal transaction = price.multiply(BigDecimal.valueOf(quantity));
        BigDecimal serviceCharge = transaction.multiply(BigDecimal.valueOf(SERVICE));
        return transaction.subtract(serviceCharge);
    }
}
