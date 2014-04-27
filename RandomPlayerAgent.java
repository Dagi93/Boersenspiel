import java.util.Timer;
import java.util.TimerTask;

import Exceptions.NotEnoughException;

public class RandomPlayerAgent implements PlayerAgent {

    Player bob;
    StockPriceProvider provider;

    public RandomPlayerAgent(Player bob, StockPriceProvider provider) {
        this.bob = bob;
        this.provider = provider;

    }

    @Override
    public void buy(AccountManagerImpl impl) throws NotEnoughException {
        int sharePosition = (int) (Math.random() * StockPriceProvider.shareCollection.length);
        String share = StockPriceProvider.shareCollection[sharePosition].getName();
        int amount = (int) (Math.random() * 30) + 1;

        try {
            impl.buy(bob.name, share, amount);
        } catch (NotEnoughException e) {
            throw e;
        }
    }

    @Override
    public void sell(AccountManagerImpl impl) throws NotEnoughException {
        if (bob.getSAcc().collection.length > 0) {
            int sharePosition = (int) (Math.random() * bob.getSAcc().collection.length);
            String share = bob.getSAcc().collection[sharePosition].getName();
            int amount = (int) (Math.random() * bob.getSAcc().collection[sharePosition].getSAmount()) + 1;

            try {
                impl.sell(bob.name, share, amount);
            } catch (NotEnoughException e) {
                throw e;
            }
        }
    }

    @Override
    public void doTransaction(AccountManagerImpl impl) throws NotEnoughException {
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
                try {

                    doTransaction(impl);
                    if(impl.lastTransaction != null)
                    System.out.println(impl.lastTransaction.toString());
                } catch (NotEnoughException e) {
                    System.out.println("Fehler startProcess");
                }
            }
        }, 2000, 3000);

    }

}
