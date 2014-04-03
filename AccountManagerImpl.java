public class AccountManagerImpl implements AccountManager {
    
    Share[] shareCollection = new Share[0];
    Player[] gambler = new Player[0];

    
    AccountManagerImpl() {
        
        shareCollection = new Share[10];
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

    }

    public void newPlayer(String name) {
        
        Player bob = new Player(name);
        if ((search(gambler, name)) == null) {
            gambler = copy(gambler, newPlayerArray(gambler), bob);
        } else {
            System.out.println("Dieser Spieler existiert bereits!");
        }

    }

    public void buy(String playerName, String shareName, int amount) {

        Player bob = search(this.gambler, playerName);
        Share share = search(this.shareCollection, shareName);
        ShareItem temp = new ShareItem(share, amount);

        if (temp.getSAmount() * share.getValue() <= bob.getCAcc().getValue()) {

            if (bob != null && share != null) {
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
                System.out.println("Spieler- oder Aktienname nicht gefunden.");
            }

        } else {
            System.out.println(playerName + " besitzt nicht genügend Geld um " + amount + " " + shareName + "-Aktien zu kaufen.");
        }

    }

    public void sell(String playerName, String shareName, int amount) {

        Player bob = search(this.gambler, playerName);
        Share temp = search(shareCollection, shareName);
        ShareItem item = new ShareItem(temp, amount);

        if (temp != null && bob != null) {

            /**
             * Ist dies der Fall und werden weniger als die im Depot vorhandenen
             * Aktien verkauft, kann die buy-Methode umgekehrt werden.
             */
            if (search(bob.getSAcc().collection, temp.getName()).getSAmount() > amount) {
                buy(bob.name, temp.getName(), -amount);
            } else if (search(bob.getSAcc().collection, temp.getName()).getSAmount() == amount) {
                buy(bob.name, temp.getName(), -amount);
                bob.getSAcc().shortenCollection(bob, item);
            } else {
                System.out.println("Der Spieler besitzt nicht genügend Aktien.");
            }

        } else {
            System.out.println("Spieler- oder Aktienname nicht gefunden.");
        }
    }

    public long getCashValueOf(String playerName) {
        
        Player bob = search(gambler, playerName);
        if (bob != null) {
            return bob.getCAcc().getValue();
        } else {
            System.out.println("Dieser Spieler existiert nicht.");
            return 0;
        }
    }

    public long getSharesValueOf(String playerName) {

        Player bob = search(gambler, playerName);
        ShareItem[] siTemp = bob.getSAcc().collection;
        long value = 0;

        if (siTemp.length > 0) {
            for (int index = 0; index < siTemp.length; index++) {
                ShareItem sTemp = siTemp[index];
                value += search(shareCollection, sTemp.getName()).getValue() * siTemp[index].getSAmount();

            }
            return value;
        } else {
            System.out.println("Dieser Spieler besitzt keine Aktien!");
            return 0;
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
        return temp.getValue();
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
        return null;
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
