import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StockGameCommandProcessor {

    BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter shellWriter = new PrintWriter(System.out);
    AccountManagerImpl accMan;

    public StockGameCommandProcessor(AccountManagerImpl accMan){
        this.accMan = accMan;
    }
    

    public void process() throws IOException {

        while (true) { // die Schleife über alle Kommandos, jeweils ein
                       // Durchlauf pro Eingabezeile
            CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);

            CommandDescriptor command = new CommandDescriptor();

            commandScanner.fillInCommandDesc(command);

            Object[] params = command.getParams();

            StockGameCommandType commandType =  (StockGameCommandType) command.getCommandType();
            
            switch (commandType) {
            case EXIT: {
                System.exit(200);
            }

            case HELP: {
                for(int index = 0; index < StockGameCommandType.values().length; index++){
                    System.out.println(StockGameCommandType.values()[index].toString());
                }
                break;
            }

            case CREATEPLAYER: {
                try{
                accMan.newPlayer(params[0].toString());
                System.out.println("Created new player " + params[0].toString());
                }catch(Exception e){
                  //TODO: handle
                }
                break;
            }

            case BUYSHARE: {
                try{
                accMan.buy(params[0].toString(), params[1].toString(), (int) params[2]);
                System.out.println(accMan.lastTransaction.toString());
                } catch(NotEnoughException e){
                    //TODO: handle
                }
                break;
            }

            case SELLSHARE: {
                try{
                accMan.sell(params[0].toString(), params[1].toString(), (int) params[2]);
                System.out.println(accMan.lastTransaction.toString());
                break;
                }catch(NotEnoughException e){
                  //TODO: handle
                }

            }
            }
        }
    }
}
