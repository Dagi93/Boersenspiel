
public abstract class StockPriceProvider implements StockPriceInfo {

    static Share[] shareCollection = new Share[0];
    
    @Override
    public long getShareValue(String shareName) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String allSharesToString() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void updateShareRate(){
        
    }

}
