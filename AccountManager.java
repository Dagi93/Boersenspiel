import Exceptions.NotEnoughException;


public interface AccountManager {
    String newPlayer(String name);
    String buy(String name, String shareName, Integer amount) throws NotEnoughException;
    String sell(String PlayerName, String shareName, Integer amount) throws NotEnoughException;
    String getCashValueOf(String playerName);
    String getAllAssetsOf(String playerName);
    String getSharesValueOf(String playerName) throws NotEnoughException;
    String checkForProfit(String playerName, String shareName);
}
