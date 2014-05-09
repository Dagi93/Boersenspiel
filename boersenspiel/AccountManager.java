package boersenspiel;
import exceptions.*;


public interface AccountManager {
    String newPlayer(String name) throws PlayerNotFoundException;
    String buy(String name, String shareName, int amount) throws NotEnoughMoneyException, PlayerNotFoundException;
    String sell(String PlayerName, String shareName, int amount) throws PlayerNotFoundException, ShareNotFoundException, NotEnoughSharesException, NotEnoughMoneyException;
    String getCashValueOf(String playerName) throws PlayerNotFoundException;
    String getAllAssetsOf(String playerName) throws PlayerNotFoundException;
    String getSharesValueOf(String playerName) throws PlayerNotFoundException;
    String checkForProfit(String playerName, String shareName) throws PlayerNotFoundException, ShareNotFoundException;
}
