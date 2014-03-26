
public interface AccountManager {
    void newPlayer(String name);
    void buy(String PlayerName, String shareName, int amount);
    void sell(String PlayerName, String shareName, int amount);
    long getAssetValue(Asset asset);
    long getPlayerAllAssets(Player player);
    long getShareValue(Share share);
    String allSharesToString();
    
    
    
}
