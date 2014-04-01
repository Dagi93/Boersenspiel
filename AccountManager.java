
public interface AccountManager {
    void newPlayer(String name);
    void buy(String name, String shareName, int amount);
    void sell(String PlayerName, String shareName, int amount);
    long getCashValueOf(Player bob);
    long getSharesValueOf(Player bob);
    long getPlayerAllAssets(Player bob);
    long getShareValue(Share share);
    String allSharesToString();
    
    
    
}
