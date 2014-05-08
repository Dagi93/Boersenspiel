package boersenspiel;
import exceptions.*;


public interface AccountManager {
    String newPlayer(String name) throws PlayerNotFoundException;
    String buy(String name, String shareName, int amount) throws NotEnoughException, PlayerNotFoundException;
    String sell(String PlayerName, String shareName, int amount) throws NotEnoughException, PlayerNotFoundException;
    String getCashValueOf(String playerName) throws PlayerNotFoundException;
    String getAllAssetsOf(String playerName) throws PlayerNotFoundException;
    String getSharesValueOf(String playerName) throws NotEnoughException, PlayerNotFoundException;
    String checkForProfit(String playerName, String shareName) throws PlayerNotFoundException, ShareNotFoundException;
}
