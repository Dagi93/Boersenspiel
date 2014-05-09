package boersenspiel;
import java.util.Timer;
import java.util.TimerTask;

import exceptions.*;

public class RandomPlayerAgent implements PlayerAgent {

    Player bob;
    StockPriceProvider provider;

    public RandomPlayerAgent(Player bob, StockPriceProvider provider) {
        this.bob = bob;
        this.provider = provider;

    }

    @Override
    public void buy(AccountManagerImpl impl){
        int sharePosition = (int) (Math.random() * StockPriceProvider.shareCollection.length);
        String share = StockPriceProvider.shareCollection[sharePosition].getName();
        int amount = (int) (Math.random() * 30) + 1;

        try {
            impl.buy(bob.name, share, amount);
        } catch (PlayerNotFoundException e) {
            System.out.println("Spieler nicht gefunden.");
        } catch (NotEnoughMoneyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void sell(AccountManagerImpl impl){
        if (bob.getSAcc().collection.length > 0) {
            int sharePosition = (int) (Math.random() * bob.getSAcc().collection.length);
            String share = bob.getSAcc().collection[sharePosition].getName();
            int amount = (int) (Math.random() * bob.getSAcc().collection[sharePosition].getSAmount()) + 1;

            try {
                impl.sell(bob.name, share, amount);
            } catch (PlayerNotFoundException e) {
                System.out.println("Spieler nicht gefunden.");
            } catch (ShareNotFoundException e) {
                System.out.println("Aktie nicht gefunden.");
                e.printStackTrace();
            } catch (NotEnoughSharesException e) {
                System.out.println("Nicht genügend Aktien.");
                e.printStackTrace();
            } catch (NotEnoughMoneyException e) {
                System.out.println("Nicht genügend Geld.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doTransaction(AccountManagerImpl impl){
        int decision = (int) (Math.random() * 10);
        if (decision < 5)
                buy(impl);
        else
                sell(impl);
    }

    @Override
    public void startProcess(final AccountManagerImpl impl) {

        Timer agentTimer = Zeitgeist.getInstance();
        agentTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("It works!");
                    
                        doTransaction(impl);
                    
            }
        }, 2000, 3000);

    }

}
