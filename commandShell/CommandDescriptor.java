package commandShell;


public class CommandDescriptor {
    
    Object[] params;
    CommandTypeInfo type;

    public Object[] getParams() {
        return params;
    }
    
    public void setParams(Object[] params) {
        this.params = params;
    }

    public CommandTypeInfo getCommandType() {
        return type;
    }

    public void setCommandType(CommandTypeInfo type) {
        this.type = type;
    }
}
