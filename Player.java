public class Player {
    public String name;
    private CashAccount cAcc;
    private ShareDepositAccount sAcc;

    public Player(String name) {
        this.name = name;
        this.cAcc = new CashAccount(name, 2000000);
        this.sAcc = new ShareDepositAccount(name);
    }
    
    public CashAccount getCAcc(){
        return cAcc;
    }
    
    public ShareDepositAccount getSAcc(){
        return sAcc;
    }
    
    public void buy(int amount, Share share) {
        ShareItem item = new ShareItem(share, amount);

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

    public void sale(int amount, Share share) {
        /** Prüfen, ob zu verkaufende Aktie überhaupt im Depot vorliegt. */
        if (sAcc.search(sAcc.getCollection(), share) > -1) {

            /**
             * Ist dies der Fall und werden weniger als die im Depot vorhandenen
             * Aktien verkauft, kann die buy-methode umgekehrt werden.
             */
            if (sAcc.getCollection()[sAcc.search(sAcc.getCollection(), share)].getSAmount() > amount) {
                buy(-amount, share);

                /**
                 * Werden alle Aktien einer Firma verkauft, wird die buy-Methode
                 * ebenfalls umgekehrt, nun muss aber auch das Array, in dem die
                 * ShareItems liegen, gekürzt werden.
                 */
            } else if (sAcc.getCollection()[sAcc.search(sAcc.getCollection(), share)].getSAmount() == amount) {
                buy(-amount, share);
                int oldIndex = 0;
                ShareItem[] temp = new ShareItem[sAcc.getCollection().length - 1];

                /** Kürzen des Arrays: */
                for (int newIndex = 0; newIndex < temp.length; newIndex++) {
                    if (!(sAcc.getCollection()[oldIndex].getName() == share.getName())) {
                        temp[newIndex] = sAcc.getCollection()[oldIndex];
                        oldIndex++;
                    } else {
                        oldIndex++;
                        newIndex--;
                    }
                }

                sAcc.setCollection(temp);

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

    public String toString() {
        String shareItems = "";

        for (int index = 0; index < sAcc.collection.length; index++) {
            shareItems += sAcc.getCollection()[index].getName();
            shareItems += "(" + sAcc.getCollection()[index].getSAmount() + ")";
            shareItems += " ";
        }

        if (sAcc.collection.length == 0) {
            return "Hallo " + this.name + "! Ihr Kontostand beträgt " + (double) cAcc.getValue() / 100 + "€, Ihr(e) Aktienpaket(e): keine";

        } else {
            return "Hallo " + this.name + "! Ihr Kontostand beträgt " + (double) cAcc.getValue() / 100 + "€, Ihr(e) Aktienpaket(e): " + shareItems + ". Der aktuelle Gesamteinkaufswert ihrer Aktien beträgt: " + (double)sAcc.getValue()/100 + "€.";
        }
    }

}
