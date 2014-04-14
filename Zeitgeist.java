import java.util.Timer;
import java.util.TimerTask;

public class Zeitgeist {
    private static Zeitgeist theInstance;

    private Zeitgeist() {
    }

    public static Zeitgeist getInstance() {
        if (theInstance == null)
            theInstance = new Zeitgeist();
        return theInstance;
    }

    // private void modifyUserObject() {
    // System.out.println("modifyUserObject: " + ++counter);
    // }

    public void startTiming(final StockPriceProvider prov) {
        final Timer timer = new Timer();
        final Viewer view = new Viewer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                prov.updateShareRates();
                view.start();
            }
        }, 2000, 1000);
    }

    // public static void main(String[] args) {
    // new Zeitgeist().startTiming();
    // }

}