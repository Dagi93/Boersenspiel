import Exceptions.NameAlreadyTakenException;
import Exceptions.NotEnoughException;



public class AccountManagerImpl implements AccountManager {
    
    Player[] gambler = new Player[0];
    NotEnoughException money = new NotEnoughException();
    Transaction lastTransaction;
    StockPriceProvider provider;

    public AccountManagerImpl(StockPriceProvider provider){
        this.provider = provider;
    }


    public String newPlayer(String name) {
        
        Player bob = new Player(name);
        if ((search(gambler, name)) == null) {
            gambler = copy(gambler, newPlayerArray(gambler), bob);
        } else {
            throw new NameAlreadyTakenException("Dieser Spieler existiert bereits.");
        }
        return "Spieler " + name + " wurde hinzugefügt.";
    }

    public String buy(String playerName, String shareName, Integer amount) throws NotEnoughException {

        Player bob = search(this.gambler, playerName);
        Share share = provider.search(StockPriceProvider.shareCollection, shareName);
        ShareItem temp = new ShareItem(share, amount);

        if (bob != null && share != null) {

            if (temp.getValue() <= bob.getCAcc().getValue()) {
                if (!bob.getSAcc().search(bob.getSAcc().collection, share)) {
                    bob.getSAcc().expandCollection(bob, temp);
                } else {
                    for (int index = 0; index < bob.getSAcc().collection.length; index++) {
                        if (bob.getSAcc().collection[index].getName().equals(temp.getName())) {
                            bob.getSAcc().setCollection(index, temp);
                        }
                    }
                }
                bob.getCAcc().setValue(-(amount * share.getValue()));
                lastTransaction = new Transaction(bob.name, temp);
            } else {
                throw new NotEnoughException("Nicht genügend Geld.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden.");
        }
        return "Spieler " + playerName + " hat " + amount + " " + shareName + "-Aktien zum Preis von " + share.getValue() + " gekauft.";

    }

    public String sell(String playerName, String shareName, Integer amount) throws NotEnoughException {

        Player bob = search(this.gambler, playerName);
        Share share = provider.search(StockPriceProvider.shareCollection, shareName);
        ShareItem item = new ShareItem(share, amount);

        if (share != null && bob != null) {

            if (search(bob.getSAcc().collection, share.getName()) != null && search(bob.getSAcc().collection, share.getName()).getSAmount() > amount) {
                buy(bob.name, share.getName(), -amount);
            } else if (search(bob.getSAcc().collection, share.getName()) != null && search(bob.getSAcc().collection, share.getName()).getSAmount() == amount) {
                buy(bob.name, share.getName(), -amount);
                bob.getSAcc().shortenCollection(bob, item);
                lastTransaction = new Transaction(bob.name, item);
            } else {
                throw new NotEnoughException("Der Spieler besitzt nicht genügend Aktien.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden.");
        }
        return "Spieler " + playerName + " hat " + amount + " " + shareName + "-Aktien zum Preis von " + share.getValue() + " verkauft.";
    }

    public String getCashValueOf(String playerName) {

        Player bob = search(gambler, playerName);
        if (bob != null) {
            return "Spieler " + playerName + "'s Kontostand beträgt " + bob.getCAcc().getValue();
        } else {
            throw new NullPointerException("Dieser Spieler existiert nicht.");
        }
    }

    public String getSharesValueOf(String playerName) throws NotEnoughException {

        Player bob = search(gambler, playerName);
        ShareItem[] siTemp = bob.getSAcc().collection;
        long value = 0;

        if(bob.name != ""){

            if (siTemp.length > 0) {
                for (int index = 0; index < siTemp.length; index++) {
                    ShareItem sTemp = siTemp[index];
                    value += provider.search(StockPriceProvider.shareCollection, sTemp.getName()).getValue() * siTemp[index].getSAmount();
                }
                return "Spieler " + playerName + "'s Aktien haben einen Wert von " + value;
            } else {
                throw new NotEnoughException("Dieser Spieler besitzt keine Aktien.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden.");
        }
    }

    public String getAllAssetsOf(String playerName) {
        
        Player bob = search(gambler, playerName);
        return  "Spieler " + playerName + "'s Gesamtvermögen beträgt" + (bob.getCAcc().getValue()) + (bob.getSAcc().getValue());
    }
    
    public String checkForProfit(String playerName, String shareName){
        Player bob = search(gambler, playerName);
        Share share = provider.search(StockPriceProvider.shareCollection, shareName);
        long meanValue = 0;
        
        for(int index = 0; index < bob.getSAcc().getCollection().length; index++){
            if(bob.getSAcc().getCollection()[index].getName().equals(share.getName())){
                meanValue = bob.getSAcc().getCollection()[index].getValue()/bob.getSAcc().getCollection()[index].getSAmount();
            }
        }
        
        if(meanValue < share.getValue())        
        return playerName + " macht Gewinn, wenn er/ sie " + shareName + "-Aktien mit mittlerem EInkaufspreis von " + meanValue + " zum Preis von " + share.getValue() + " verkauft.";
        else        
            return playerName + " macht KEINEN Gewinn, wenn er/ sie " + shareName + "-Aktien mit mittlerem EInkaufspreis von " + meanValue + " zum Preis von " + share.getValue() + " verkauft.";
        
        
    }
    
    public Player search(Player[] gambler, String name) {

        if (gambler.length > 0) {
            for (int index = 0; index < gambler.length; index++) {
                if (gambler[index].name.equals(name)) {
                    return gambler[index];
                }
            }
        }
        return null;
    }

    public ShareItem search(ShareItem[] collection, String shareName) {

        if (collection.length > 0) {
            for (int index = 0; index < collection.length; index++) {
                if (collection[index].getName().equals(shareName)) {
                    return collection[index];
                }
            }
        }
        throw new NullPointerException("Dieses Aktienpaket befindet sich nicht im Aktiendepot");
    }

    public Player[] newPlayerArray(Player[] oldPlayerArray) {
        
        Player[] newPlayerArray = new Player[oldPlayerArray.length + 1];
        return newPlayerArray;
    }

    public Player[] copy(Player[] gambler, Player[] newPlayerArray, Player bob) {
        
        for (int index = 0; index < newPlayerArray.length; index++) {
            if (index != newPlayerArray.length - 1) {
                newPlayerArray[index] = gambler[index];
            } else {
                newPlayerArray[index] = bob;
            }
        }
        return newPlayerArray;
    }

    public void turnAgentOn(String playerName){
        Player bob = search(gambler, playerName);
        PlayerAgent agent = new RandomPlayerAgent(bob, provider);
        agent.startProcess(this);
    }
    
}
