
public class AccountManagerImpl implements AccountManager{
    Share[] shareCollection = new Share[0];
    Player[] gambler = new Player[0];
    Player bob = new Player("Bob");
    Share temp = new Share(0, "temp");
    ShareItem item = new ShareItem(temp, 5);

    @Override
    public void newPlayer(String name) {
        Player bob = new Player (name);
        if ((search(gambler, name)) == -1){
        	copy(gambler, newPlayerArray(gambler), bob);
        }else{
        	System.out.println("Dieser Spieler existiert bereits!");
        }
        
    }

    @Override
    public void buy(String playerName, String shareName, int amount) {

        int index = search(this.gambler, playerName);
        searchPlayerShare(index, bob, temp, item, shareName, amount);
        
        if(bob.getSAcc().search(bob.getSAcc().collection, temp) == -1){
            ShareItem[] temp2 = bob.getSAcc().newArray(bob.getSAcc().getCollection());
            bob.getSAcc().setCollection(bob.getSAcc().copy(bob.getSAcc().getCollection(), temp2, item));
        }else{
            bob.getSAcc().setCollection(bob.getSAcc().search(bob.getSAcc().getCollection(), temp), item);
        }
        
    }

    @Override
    public void sell(String playerName, String shareName, int amount) {
        int index = search(this.gambler, playerName);
        searchPlayerShare(index, bob, temp, item, shareName, amount);
        
        if (bob.getSAcc().search(bob.getSAcc().getCollection(), temp) > -1) {

            /**
             * Ist dies der Fall und werden weniger als die im Depot vorhandenen
             * Aktien verkauft, kann die buy-methode umgekehrt werden.
             */
            if (bob.getSAcc().getCollection()[bob.getSAcc().search(bob.getSAcc().getCollection(), temp)].getSAmount() > amount) {
                buy(bob.name, temp.getName(),-amount);

                /**
                 * Werden alle Aktien einer Firma verkauft, wird die buy-Methode
                 * ebenfalls umgekehrt, nun muss aber auch das Array, in dem die
                 * ShareItems liegen, gekürzt werden.
                 */
            } else if (bob.getSAcc().getCollection()[bob.getSAcc().search(bob.getSAcc().getCollection(), temp)].getSAmount() == amount) {
                buy(bob.name, temp.getName(),-amount);
                int oldIndex = 0;
                ShareItem[] temp = new ShareItem[bob.getSAcc().getCollection().length - 1];

                /** Kürzen des Arrays: */
                for (int newIndex = 0; newIndex < temp.length; newIndex++) {
                    if (!(bob.getSAcc().getCollection()[oldIndex].getName().equals(item.getName()))) {
                        temp[newIndex] = bob.getSAcc().getCollection()[oldIndex];
                        oldIndex++;
                    } else {
                        oldIndex++;
                        newIndex--;
                    }
                }

                bob.getSAcc().setCollection(temp);

                /**
                 * Sollen mehr Aktien einer Firma verkauft werden als im Depot
                 * vorhanden, dann wird eine Fehlermeldung ausgegeben.
                 */
            } else {
                System.out.println("Sie besitzen diese Anzahl an Aktien nicht.");
            }

            /**
             * Sind von der zu verkaufenden Aktie gar keine im Depot vorhanden
             * wird eine Fehlermeldung ausgegeben.
             */
        } else {
            System.out.println("Sie besitzen keine Aktien dieses Unternehmens.");
        }
    

        
        
    }

    @Override
    public long getCashValueOf(Player bob) {
        return bob.getCAcc().getValue();
    }

    @Override
    public long getSharesValueOf(Player bob) {
        ShareItem[] temp = bob.getSAcc().collection;
        long value = 0;
        
        for(int index = 0; index < temp.length; index++){
                int foundIndex = search(shareCollection, temp[index].getName());
                value += shareCollection[foundIndex].getValue() * temp[index].getSAmount();
            
        }
        return value;
    }

    @Override
    public long getPlayerAllAssets(Player player) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String allSharesToString() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getShareValue(Share share) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
    public int search(Player[] gambler, String name) {

        if (gambler.length > 0) {
            for (int index = 0; index < gambler.length; index++) {
                if (gambler[index].name.equals(name)) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    public int search(Share[] collection, String shareName) {

        if (collection.length > 0) {
            for (int index = 0; index < collection.length; index++) {
                if (collection[index].getName().equals(shareName)) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    public void searchPlayerShare(int index, Player bob, Share temp, ShareItem item, String shareName, int amount){
        if (index > -1)
            bob = gambler[index];
        else {
            System.out.println("Dieser Spieler ist kein Kunde unserer Bank.");
            return;
        }

        if(search(shareCollection, shareName) > 0){
            temp = shareCollection[search(shareCollection, shareName)];
            item = new ShareItem(temp, amount);
        }else{
            System.out.println("Diese Aktie existiert nicht.");
            return;
        }
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
