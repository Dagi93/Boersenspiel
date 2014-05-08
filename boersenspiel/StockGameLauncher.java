package boersenspiel;
import java.io.IOException;
import java.util.logging.Logger;

import exceptions.*;


public class StockGameLauncher {

    private static Logger logger = Logger.getLogger(AccountManagerImpl.class.getName());
    public static void main(String[] args) throws NotEnoughException, IOException {
        StockPriceProvider provider = new RandomStockPriceProvider();
        AccountManagerImpl accMan = new AccountManagerImpl(provider);

        logger.info( "Dann mal los." );
        provider.startUpdate();
        Viewer view = new Viewer(provider);
        view.start();
        StockGameCommandProcessor processor = new StockGameCommandProcessor(accMan);
        try {
            processor.process();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        

        
//        accMan.newPlayer("Max Muster");
//        waitAWhile(3000);
//        accMan.newPlayer("Maria Muster");
//        waitAWhile(3000);
//        
//        accMan.buy("Max Muster", "bmw", 20);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//        
//        accMan.buy("Max Muster", "siemens", 20);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//        
//        accMan.buy("Max Muster", "bmw", 20);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//
//        accMan.buy("Maria Muster", "vw", 10);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//
//        accMan.buy("Maria Muster", "apple", 10);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//
//        accMan.sell("Max Muster", "bmw", 10);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
//
//        accMan.sell("Maria Muster", "vw", 8);
//        System.out.println(accMan.lastTransaction.toString());
//        waitAWhile(2500);
        
        
        
    }
    
    
    
    
    public static void waitAWhile(long period) {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
        }
        ;
    }

}
