package boersenspiel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.*;

import commandShell.CommandDescriptor;
import commandShell.CommandScanner;
import exceptions.*;

public class StockGameCommandProcessor {

    BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter shellWriter = new PrintWriter(System.out);
    AccountManagerImpl accMan = null;

    public StockGameCommandProcessor(AccountManagerImpl accMan) {
        this.accMan = accMan;
    }

    public void process() throws Exception {

        CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);
        while (true) { // die Schleife über alle Kommandos, jeweils ein
                       // Durchlauf pro Eingabezeile
            
            commandScanner.readIn();
            
            CommandDescriptor command = new CommandDescriptor();

            try {
                command = commandScanner.fillInCommandDesc(command);
            } catch (BadInputException e1) {
                System.out.println(e1.getMessage());
                continue;
            }

            Object[] params = command.getParams();

            StockGameCommandType commandType = (StockGameCommandType) command.getCommandType();
            
            switch (commandType) {
            case EXIT: {
                System.exit(200);
            }

            case HELP: {
                for (int index = 0; index < StockGameCommandType.values().length; index++) {
                    System.out.println(StockGameCommandType.values()[index].toString());
                }
                break;
            }

            default: {

                try {
                    Method method = Class.forName("boersenspiel.AccountManagerImpl").getMethod(commandType.getName(), commandType.getParamTypes());

                    Object test = method.invoke(accMan, params);
                    if(test != null)
                    System.out.println(test);

                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    System.out.println("Den Befehl " + commandType.getName()
                            + " gibt es nicht. Rufen Sie den Befehl 'help' auf, um alle Befehle anzeigen zu lassen.");
                    continue;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    System.out.println("Einer Ihrer Parameter konnte nicht zugeordnet werden.");
                } catch (NullPointerException e) {
                    System.out.println("Diesen Befehl gibt es nicht.2");
                    e.printStackTrace();
                }
            }
            }
        }
    }
}
