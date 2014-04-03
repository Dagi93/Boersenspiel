/**TO DO
 *
 * 
 *- public long getPlayerAllAssets(Player player)
 *- public String allSharesToString()
 *- public long getShareValue(Share share)
 * 
 * Sonstiges:
 * - Eindeutigkeit von Spielernamen, Aktiennamen etc. gewährleisten können
 * - Sequenzdiagramm für buy-Methode erstellen
 * 
 * 
 * 
 * */


public class BörsenspielTest {

    public static void main(String[] args) {
        AccountManagerImpl accMan = new AccountManagerImpl();
        accMan.newPlayer("Bob");
        accMan.newPlayer("Betty");
        accMan.newPlayer("Bob");
        
        accMan.buy("Bob", "bmw", 100);
        System.out.println(accMan.gambler[0].toString());
        accMan.sell("Bob", "bmw", 10);
        System.out.println(accMan.gambler[0].toString());
        System.out.println("Gesamtwert aller Aktien: " + (double)accMan.getSharesValueOf("Bob")/100);
        System.out.println(accMan.allSharesToString());
        System.out.println("Gesamtwert aller Assets: " + (double)accMan.getPlayerAllAssets("Bob")/100);
    }

}
