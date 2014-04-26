import java.io.BufferedReader;
import java.io.IOException;

public class CommandScanner {

    CommandTypeInfo[] cmds;
    CommandTypeInfo cmd;
    String s;

    public CommandScanner(CommandTypeInfo[] cmds, BufferedReader shellReader) throws Exception {
        this.cmds = cmds;
        s = shellReader.readLine();

        try{
        cmd = searchCommandType(cmds, s);
        }catch (Exception e){
            throw e;
        }

    }

    public void fillInCommandDesc(CommandDescriptor command) {

        try {
            command.setCommandType(cmd);
            if (command.type.getParamTypes() != null) {
                String s3 = s.substring(cmd.getName().length() + 1);
                String[] sa = s3.split(" ");
                Object[] obs = new Object[cmd.getParamTypes().length];
                int number = 0;

                for (int index = 0; index < cmd.getParamTypes().length; index++) {
                    if (cmd.getParamTypes()[index] == String.class)
                        obs[index] = sa[index];
                    else {
                        char[] ca = sa[index].toCharArray();
                        for (int cindex = 0; cindex < ca.length; cindex++) {
                            ca[cindex] -= '0';
                        }
                        int cindex2 = 0;
                        for (int cindex = ca.length - 1; cindex >= 0; cindex--) {
                            number += ca[cindex] * Math.pow(10, cindex2);
                            cindex2++;
                        }
                        obs[index] = number;
                    }
                }
                command.setParams(obs);
            }
        } catch (Exception e) {
            System.out.println("Diesen Befehl gibt es nicht.");
            
        }
    }

    public CommandTypeInfo searchCommandType(CommandTypeInfo[] cmds, String s) throws Exception {

        
        for (int index = 0; index < cmds.length; index++) {
            String s2 = s.substring(0, (s.length() > cmds[index].getName().length() ? cmds[index].getName().length() : s.length()));
            if (cmds[index].getName().equals(s2)) {
                return cmds[index];

            }

        }
        throw new Exception(s);
        
    }
}
