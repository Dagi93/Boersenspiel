/**TO DO
 *
 *AccountManager:
 * - Methode    void   newPlayer(String name)
 * - Methode    void   buy(String PlayerName, String shareName, int amount)
 * - Methode    void   sell(String PlayerName, String shareName, int amount) 
 * - Methode    long   getAssetValue(Asset asset)
 * - Methode    long   getPlayerAllAssets(Player player)
 * - Methode    long   getShareValue(Share share)
 * - Methode    String allSharesToString()
 * 
 * - neue Klasse AccountManagerImpl
 * 
 * AccountManagerImpl:
 * - neues Array mit Aktien
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
        Share bmw = new Share(5000, "bmw");
        Share vw = new Share(10000, "vw");
        Share audi = new Share(15000, "audi");
        System.out.println(bmw.toString());

        Player Klaus = new Player("Klaus");
        System.out.println(Klaus.toString());

        Klaus.buy(20, bmw);
        System.out.println(Klaus.toString());

        Klaus.buy(20, vw);
        System.out.println(Klaus.toString());
        
//        Klaus.buy(20, vw);
//        System.out.println(Klaus.toString());
//        
//        Klaus.buy(10, audi);
//        System.out.println(Klaus.toString());
//
//        Klaus.sale(20, bmw);
//        System.out.println(Klaus.toString());

        
    }

}
