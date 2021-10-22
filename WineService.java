import java.math.BigDecimal;

public interface WineService {

    BigDecimal processSale(BigDecimal price, int quantity);

    BigDecimal processReturn(BigDecimal price, int quantity);

}
