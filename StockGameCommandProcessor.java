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
            CommandScanner commandScanner = new CommandScanner(StockGameCommandType.values(), shellReader);

            CommandDescriptor command = new CommandDescriptor();

            commandScanner.fillInCommandDesc(command);

            Object[] params = command.getParams();

            StockGameCommandType commandType = (StockGameCommandType) command.getCommandType();

            try {

                Class[] paramsClass = new Class[params.length];
                for (int index = 0; index < paramsClass.length; index++) {
                    paramsClass[index] = params[index].getClass();
                    System.out.println(paramsClass[index].toString());
                }

                Method method = Class.forName("AccountManagerImpl").getMethod(commandType.getName(), paramsClass);

                Object test = method.invoke(accMan, params);
                shellWriter.print("hello");

            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                System.out.println("Den Befehl " + commandType.getName() + " gibt es nicht. Rufen Sie den Befehl 'help' auf, um alle Befehle anzeigen zu lassen.");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                System.out.println("Einer Ihrer Parameter konnte nicht zugeordnet werden.");
            }

            // switch (commandType) {
            // case EXIT: {
            // System.exit(200);
            // }
            //
            // case HELP: {
            // for(int index = 0; index < StockGameCommandType.values().length;
            // index++){
            // System.out.println(StockGameCommandType.values()[index].toString());
            // }
            // break;
            // }
            //
            // case CREATEPLAYER: {
            // try{
            // accMan.newPlayer(params[0].toString());
            // System.out.println("Created new player " + params[0].toString());
            // }catch(Exception e){
            // //TODO: handle
            // }
            // break;
            // }
            //
            // case BUYSHARE: {
            // try{
            // accMan.buy(params[0].toString(), params[1].toString(), (int)
            // params[2]);
            // System.out.println(accMan.lastTransaction.toString());
            // } catch(NotEnoughException e){
            // //TODO: handle
            // }
            // break;
            // }
            //
            // case SELLSHARE: {
            // try{
            // accMan.sell(params[0].toString(), params[1].toString(), (int)
            // params[2]);
            // System.out.println(accMan.lastTransaction.toString());
            // break;
            // }catch(NotEnoughException e){
            // System.out.println("Sie besitzen zu wenig Geld oder Aktien.");
            // }catch(NullPointerException n){
            // System.out.println("Spieler- oder Aktienname nicht gefunden.");
            // }
            //
            // }
            // case SHOWASSETS: {
            //
            //
            // break;
            // }
            // default:
            // System.out.println("Befehl nicht gefunden.");
            // break;
            // }
        }
    }
}
