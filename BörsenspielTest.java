/**TO DO
 *
 * 
 *- public long getPlayerAllAssets(Player player)
 *- public String allSharesToString()
 *- public long getShareValue(Share share)
 * 
 * Sonstiges:
 * - Eindeutigkeit von Spielernamen, Aktiennamen etc. gew�hrleisten k�nnen
 * - Sequenzdiagramm f�r buy-Methode erstellen
 * 
 * 
 * 
 * */


public class B�rsenspielTest {

    public static void main(String[] args) {
        AccountManagerImpl accMan = new AccountManagerImpl();
        accMan.newPlayer("Bob");
        accMan.newPlayer("Betty");
        
        System.out.println(accMan.gambler[1].name);
        
    }

}
