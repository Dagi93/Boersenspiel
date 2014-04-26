import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.*;

import Exceptions.NotEnoughException;

public class StockGameCommandProcessor {

    BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter shellWriter = new PrintWriter(System.out);
    AccountManagerImpl accMan = null;

    public StockGameCommandProcessor(AccountManagerImpl accMan) {
        this.accMan = accMan;
    }

    public void process() throws IOException {

        while (true) { // die Schleife über alle Kommandos, jeweils ein
                       // Durchlauf pro Eingabezeile
            CommandScanner commandScanner = null;
            try {
                commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);
            } catch (Exception e) {
                System.out.println("Den Befehl " + e.getMessage()
                        + " gibt es nicht. Rufen Sie den Befehl 'help' auf, um alle Befehle anzeigen zu lassen.");
                continue;
            }

            CommandDescriptor command = new CommandDescriptor();

            commandScanner.fillInCommandDesc(command);

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

                    Class[] paramsClass = new Class[params.length];
                    if (params != null) {
                        for (int index = 0; index < params.length; index++) {
                            paramsClass[index] = params[index].getClass();
                            System.out.println(paramsClass[index].toString());
                        }
                    }

                    Method method = Class.forName("AccountManagerImpl").getMethod(commandType.getName(), paramsClass);

                    Object test = method.invoke(accMan, params);
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
