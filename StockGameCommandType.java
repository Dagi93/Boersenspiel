
public enum StockGameCommandType implements CommandTypeInfo{
    

    HELP         ("help",      "                                          * list all commands"),
    EXIT         ("exit",      "                                          * exit program"),
    NEWPLAYER    ("newPlayer", " <name>                              * create a new player by name", String.class), 
    BUYSHARE     ("buy",       "       <playername> <sharename> <amount>   * buy that amount of shares", String.class, String.class, int.class),
    SELLSHARE    ("sell",      "      <playername> <sharename> <amount>   * sell that amount of shares", String.class, String.class, int.class),
    SHOWASSETS   ("sa",   "    ")
    ;

    private String cmd;
    private String txt;
    private Class<?>[] params;
    
    StockGameCommandType(String cmd, String txt){
        this.cmd = cmd;
        this.txt = txt;
    }
    
    StockGameCommandType(String cmd, String txt, Class<?>... params){
       this.cmd = cmd;
       this.txt = txt;
       this.params = params;
    }

    @Override
    public String getName() {
        return cmd;
    }

    @Override
    public String getHelpText() {
        return txt;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return params;
    }
    
    public String toString(){
        return getName() + " " + getHelpText();
    }

    
}
