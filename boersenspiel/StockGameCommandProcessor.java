package boersenspiel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import logging.LogHandler;
import commandShell.CommandDescriptor;
import commandShell.CommandScanner;
import exceptions.*;

public class StockGameCommandProcessor {

    BufferedReader shellReader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter shellWriter = new PrintWriter(System.out);
    AccountManagerImpl accMan = null;
    private static Logger log = Logger.getLogger(StockGameCommandProcessor.class.getName());

    public StockGameCommandProcessor(AccountManagerImpl accMan) {
        this.accMan = accMan;
    }

    public void process() throws Throwable {

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

                    ClassLoader loader = accMan.getClass().getClassLoader();  
                    Class[] interfaces = new Class[] {AccountManager.class};
                    LogHandler logHandler = new LogHandler(accMan);  
                    AccountManager stockGameProxy = (AccountManager) Proxy.newProxyInstance(loader, interfaces, logHandler);
                    Method method = Class.forName("boersenspiel.AccountManagerImpl").getMethod(commandType.getName(), commandType.getParamTypes());
                    Object test = logHandler.invoke(stockGameProxy, method, params);
                    
                    
                    
//                    Method method = Class.forName("boersenspiel.AccountManagerImpl").getMethod(commandType.getName(), commandType.getParamTypes());
//                    Object test = method.invoke(accMan, params);
//                    Method method = Class.forName("boersenspiel.AccountManagerImpl").getMethod(commandType.getName(), commandType.getParamTypes());
//                    Object test = method.invoke(stockGameProxy, params);

                    if (test != null){
                        log.log(Level.FINE, (String) test);
                        System.out.println(test);
                    }

                } catch (SecurityException e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                } catch (IllegalArgumentException e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                } catch (NoSuchMethodException e) {
                    log.log(Level.SEVERE, e.getMessage());
                    System.out.println(e.getCause().getMessage());
                    continue;
                } catch (ClassNotFoundException e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                    e.printStackTrace();
                } catch (NameAlreadyTakenException e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                    System.out.println(e.getCause().getMessage());
                } catch (NullPointerException e) {
                    log.log(Level.SEVERE, e.getCause().getMessage());
                    System.out.println(e.getCause().getMessage());
                } catch (InvocationTargetException e){
                    log.log(Level.SEVERE, e.getCause().getMessage());
                    System.out.println(e.getCause().getMessage());
                    
                }
                
            }
            }
        }
    }
}
