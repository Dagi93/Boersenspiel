import Exceptions.NotEnoughException;


public interface PlayerAgent {

//    Player bob = null;    
    
    void startProcess(AccountManagerImpl impl);
    void buy(AccountManagerImpl impl) throws NotEnoughException;
    void sell(AccountManagerImpl impl) throws NotEnoughException;
    void doTransaction(AccountManagerImpl impl) throws NotEnoughException;
    
    
    
}
