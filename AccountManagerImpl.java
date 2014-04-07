

public class AccountManagerImpl implements AccountManager {
    
    Share[] shareCollection = new Share[0];
    Player[] gambler = new Player[0];
    NotEnoughException money = new NotEnoughException();

    
    AccountManagerImpl() {
        
        shareCollection = new Share[11];
        shareCollection[0] = new Share(1000, "bmw");
        shareCollection[1] = new Share(500, "siemens");
        shareCollection[2] = new Share(100, "nokia");
        shareCollection[3] = new Share(4900, "audi");
        shareCollection[4] = new Share(8900, "vw");
        shareCollection[5] = new Share(4560, "sony");
        shareCollection[6] = new Share(1542, "lenovo");
        shareCollection[7] = new Share(1212, "microsoft");
        shareCollection[8] = new Share(6666, "apple");
        shareCollection[9] = new Share(5000, "hugo boss");
        shareCollection[10] = new Share(3700, "kuka");

    }

    public void newPlayer(String name) {
        
        Player bob = new Player(name);
        if ((search(gambler, name)) == null) {
            gambler = copy(gambler, newPlayerArray(gambler), bob);
        } else {
            throw new NameAlreadyTakenException("Dieser Spieler existiert bereits.");
        }
    }

    public void buy(String playerName, String shareName, int amount) throws NotEnoughException {

        Player bob = search(this.gambler, playerName);
        Share share = search(this.shareCollection, shareName);
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
            } else {
                throw new NotEnoughException("Nicht genügend Geld.");
//                System.out.println(playerName + " besitzt nicht genügend Geld um " + amount + " " + shareName + "-Aktien zu kaufen.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden.");
        }

    }

    public void sell(String playerName, String shareName, int amount) throws NotEnoughException {

        Player bob = search(this.gambler, playerName);
        Share temp = search(shareCollection, shareName);
        ShareItem item = new ShareItem(temp, amount);

        if (temp != null && bob != null) {

            if (search(bob.getSAcc().collection, temp.getName()) != null && search(bob.getSAcc().collection, temp.getName()).getSAmount() > amount) {
                buy(bob.name, temp.getName(), -amount);
            } else if (search(bob.getSAcc().collection, temp.getName()) != null && search(bob.getSAcc().collection, temp.getName()).getSAmount() == amount) {
                buy(bob.name, temp.getName(), -amount);
                bob.getSAcc().shortenCollection(bob, item);
            } else {
                throw new NotEnoughException("Der Spieler besitzt nicht genügend Aktien.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden.");
        }
    }

    public long getCashValueOf(String playerName) {

        Player bob = search(gambler, playerName);
        if (bob != null) {
            return bob.getCAcc().getValue();
        } else {
            throw new NullPointerException("Dieser Spieler existiert nicht.");
        }
    }

    public long getSharesValueOf(String playerName) throws NotEnoughException {

        Player bob = search(gambler, playerName);
        ShareItem[] siTemp = bob.getSAcc().collection;
        long value = 0;

        if(bob.name != ""){

            if (siTemp.length > 0) {
                for (int index = 0; index < siTemp.length; index++) {
                    ShareItem sTemp = siTemp[index];
                    value += search(shareCollection, sTemp.getName()).getValue() * siTemp[index].getSAmount();
                }
                return value;
            } else {
                throw new NotEnoughException("Dieser Spieler besitzt keine Aktien.");
            }

        } else {
            throw new NullPointerException("Spieler- oder Aktienname nicht gefunden");
        }
    }

    public long getPlayerAllAssets(String playerName) {
        
        Player bob = search(gambler, playerName);
        return (bob.getCAcc().getValue()) + (bob.getSAcc().getValue());
    }

    public String allSharesToString() {
        
        String allShares = new String("Verfügbare Aktien (aktueller Wert in Klammern): ");
        for (int index = 0; index < shareCollection.length; index++) {
            allShares += shareCollection[index].getName() + " (" + (double) shareCollection[index].getValue() / 100 + "€), ";

        }
        return allShares;

    }

    public long getShareValue(String shareName) {

        Share temp = search(shareCollection, shareName);
        if (temp != null) {
            return temp.getValue();
        } else {
            throw new NullPointerException("Aktie nicht im System.");
        }
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

    public Share search(Share[] collection, String shareName) {

        if (collection.length > 0) {
            for (int index = 0; index < collection.length; index++) {
                if (collection[index].getName().equals(shareName)) {
                    return collection[index];
                }
            }
        }
        throw new NullPointerException("Diese Aktie befindet sich nicht im System.");
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

}
