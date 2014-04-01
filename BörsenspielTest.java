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
        
        System.out.println(accMan.gambler[1].name);
        
    }

}
