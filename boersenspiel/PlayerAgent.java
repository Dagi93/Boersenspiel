package boersenspiel;
import exceptions.*;


public interface PlayerAgent {

//    Player bob = null;    
    
    void startProcess(AccountManagerImpl impl);
    void buy(AccountManagerImpl impl) throws NotEnoughException, PlayerNotFoundException;
    void sell(AccountManagerImpl impl) throws NotEnoughException, PlayerNotFoundException;
    void doTransaction(AccountManagerImpl impl) throws NotEnoughException, PlayerNotFoundException;
    
    
    
}
