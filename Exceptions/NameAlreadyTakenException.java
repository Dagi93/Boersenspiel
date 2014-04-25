package Exceptions;

public class NameAlreadyTakenException extends RuntimeException{
    
    public NameAlreadyTakenException(){
        super();
    }
    
    public NameAlreadyTakenException(String s){
        super(s);
    }
    
}
