/** TO DO**
 * 
 * @author Dagi: - ShareDepositAccount.toString fertigstellen
 *               - In den ToString-Methoden den Wert immer durch 100 teilen und als double casten, damit eine authentische Ausgabe entsteht
 *
 */
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
