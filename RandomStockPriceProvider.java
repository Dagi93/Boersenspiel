import java.util.Random;

public class RandomStockPriceProvider extends StockPriceProvider {
    Random random = new Random();

    @Override
    public void updateShareRate(Share share) {
        long number = random.nextInt()/100000000;
        
        share.setValue(number);
        
    }

}
