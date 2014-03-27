
public class AccountManagerImpl implements AccountManager{
    Share[] shareCollection = new Share[10];

    @Override
    public void newPlayer(String name) {
        Player bob = new Player (name);
        
    }

    @Override
    public void buy(Player bob, String shareName, int amount) {
        ShareItem item = new ShareItem(shareName, amount);
        CashAccount cAcc = bob.

        if (cAcc.getValue() >= item.getValue()) {
            cAcc.setValue(-(item.getValue()));
        } else {
            System.out.println("Not enough money!");
            return;
        }

        if (sAcc.search(sAcc.getCollection(), share) == -1) {
            /**
             * Wenn das neue Aktienpaket noch nicht im Aktiendepot des Spielers
             * ist.
             */
            ShareItem[] temp = sAcc.newArray(sAcc.getCollection());
            sAcc.setCollection(sAcc.copy(sAcc.getCollection(), temp, item));
        } else {
            /** Sonst wird das vorhandene Aktienpaket aktualisiert. */
            sAcc.setCollection(sAcc.search(sAcc.getCollection(), share), item);
        }

    }

    @Override
    public void sell(String PlayerName, String shareName, int amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long getAssetValue(Asset asset) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getPlayerAllAssets(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getShareValue(Share share) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String allSharesToString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
