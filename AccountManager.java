
public interface AccountManager {
    void newPlayer(String name); //fertig
    void buy(String name, String shareName, int amount); //fertig
    void sell(String PlayerName, String shareName, int amount); //fertig
    long getCashValueOf(String playerName); //fertig
    long getSharesValueOf(String playerName); //fertig
    long getPlayerAllAssets(String playerName); //fertig
    long getShareValue(String shareName); //fertig
    String allSharesToString(); //fertig
    
    
    
}
