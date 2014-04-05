/**
 * TO DO
 * 
 * Sonstiges: - Eindeutigkeit von Spielernamen, Aktiennamen etc. gew�hrleisten
 * k�nnen
 * */

public class B�rsenspielTest {

    public static void main(String[] args){
        AccountManagerImpl accMan = new AccountManagerImpl();
        accMan.newPlayer("Bob");
        accMan.newPlayer("Betty");
        
        /**Teil 1:
         **/
        
        try {
            accMan.buy("Bob", "bmw", 20);
        } catch (NotEnoughException e) {
            e.printStackTrace();
        }

        System.out.println(accMan.gambler[0].toString());
        
        try {
            accMan.sell("Bob", "bmw", 10);
        } catch (NotEnoughException e1) {
            e1.printStackTrace();
        }

        System.out.println(accMan.gambler[0].toString());
        
        System.out.println("Bankkonto: " + (double)accMan.getCashValueOf("Bob")/100);
        
        try {
            System.out.println("Aktiendepot: " + (double)accMan.getSharesValueOf("Bob")/100);
        } catch (NotEnoughException e1) {
            e1.printStackTrace();
        }
        
        System.out.println("Gesamtverm�gen: " + (double)accMan.getPlayerAllAssets("Bob")/100);
        
        System.out.println("bmw-Wert: " + (double)accMan.getShareValue("bmw")/100);
        
        System.out.println(accMan.allSharesToString());
        
        
        
        
        
        
        
        
        
        
        
        
        
        try {
            accMan.buy("Bib", "bpw", 100000000);
        } catch (NotEnoughException e) {
            System.out.println("Nicht gen�gend Geld.");
        } catch (NullPointerException p){
            System.out.println("Spieler- oder Aktienname nicht gefunden.");
        }
        

        System.out.println(accMan.gambler[0].toString());
        
        try {
            accMan.sell("Beb", "bml", 100000000);
        } catch (NotEnoughException e) {
            System.out.println("Nicht gen�gend Aktien.");
//            e.printStackTrace();
        } catch (NullPointerException p){
            System.out.println("Spieler- oder Aktienname nicht gefunden.");
//            p.printStackTrace();
        }
        
        try {
            accMan.buy("Bkb", "lmw", 85200000);
        } catch (NotEnoughException e) {
            System.out.println("Nicht gen�gend Geld.");
//            e.printStackTrace();
        } catch (NullPointerException p){
            System.out.println("Spieler- oder Aktienname nicht gefunden.");
//            p.printStackTrace();
        }
        
        System.out.println(accMan.gambler[0].toString());
        
        
        
    
    }
}
