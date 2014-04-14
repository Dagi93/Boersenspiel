import java.awt.Color;
import java.awt.Font;
import java.util.TimerTask;


//import java.util.Timer;
//import java.util.Calendar;
//import java.util.Date;
//import java.text.DateFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Viewer extends JFrame {
    private JLabel clockLabel;
    private StockPriceProvider prov = new RandomStockPriceProvider();
    private Font font = new Font("font", 0, 24);

    public class TickerTask extends TimerTask {
        public void run() {
            String output = createText();
            clockLabel.setText(output);
            clockLabel.repaint();
        }

        public String createText() {     
            String output = "<html><body>Aktienkurse:<br>";
            output += prov.allSharesToString() + "</body></html>";
            return output;
        }
    }


    public Viewer() {
        clockLabel = new JLabel("coming soon ...");
        clockLabel.setFont(font);
        clockLabel.setForeground(Color.BLUE);
        add("Center", clockLabel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 450);
        setVisible(true);

    }

    public void start() {
        TickerTask task = new TickerTask();
        task.run();
        task.createText();
    }

}