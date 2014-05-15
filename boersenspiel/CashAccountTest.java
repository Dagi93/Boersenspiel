package boersenspiel;

import static org.junit.Assert.*;

import org.junit.Test;

public class CashAccountTest {
    CashAccount cAcc = new CashAccount("bob", 200);
    
    @Test
    public void testSetValue() {
        cAcc.setValue(-300);
        assertEquals("set the value", -100, cAcc.getValue());
    }
    
    @Test
    public void testSetName() {
        cAcc.setName("lisa");
        assertEquals("lisa", cAcc.getName());
    }

}
