import Exceptions.NotEnoughException;


public interface AccountManager {
    void newPlayer(String name);
    void buy(String name, String shareName, Integer amount) throws NotEnoughException;
    void sell(String PlayerName, String shareName, Integer amount) throws NotEnoughException;
    long getCashValueOf(String playerName);
    long getPlayerAllAssets(String playerName);
    long getSharesValueOf(String playerName) throws NotEnoughException;
    
    
    
}
