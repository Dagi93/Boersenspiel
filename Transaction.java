
public class Transaction {
    private int amount;
    private long shareValue;
    private long totalValue;
    private String playerName;
    private String shareName;
    
    
    public Transaction(String playerName, ShareItem item){
        this.playerName = playerName;
        this.shareName = item.getName();
        this.amount = item.getSAmount();
        this.totalValue = item.getValue();
        this.shareValue = totalValue/amount;
    }
    
    
    public String getShareName() {
        return shareName;
    }
    
    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    
    public long getTotalValue() {
        return totalValue;
    }
    
    public void setTotalValue(long value) {
        this.totalValue = value;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public String toString(){
        if(totalValue < 0){
            totalValue *= -1;
            amount *= -1;
            return playerName + " hat " + amount + " " + shareName + "-Aktien zum Preis von " + totalValue/100 + "," + (totalValue%100 < 10? totalValue%100 + "0": totalValue%100) + "€ (" + shareValue/100 + "," + (shareValue%100 < 10? shareValue%100 + "0": shareValue%100) + "€) verkauft." ;
        }else
            return playerName + " hat " + amount + " " + shareName + "-Aktien zum Preis von " + totalValue/100 + "," + (totalValue%100 < 10? totalValue%100 + "0": totalValue%100) + "€ (" + shareValue/100 + "," + (shareValue%100 < 10? shareValue%100 + "0": shareValue%100) + "€) gekauft." ;
            
    }
}
