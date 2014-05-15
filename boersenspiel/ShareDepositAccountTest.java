package boersenspiel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShareDepositAccountTest {
    Player bob = new Player("bob");
    Share bmw = new Share(5000, "bmw");
    Share vw = new Share(6000, "vw");
    ShareItem bmwItem = new ShareItem(bmw, 2);
    ShareItem vwItem = new ShareItem(vw, 5);
    
    @Before
    public void setUp(){
        ShareItem[] collection = new ShareItem[]{bmwItem, vwItem};
        
        bob.getSAcc().setCollection(collection);        
    }
    
    @Test
    public void testGetValue() {
        assertEquals(40000, bob.getSAcc().getValue());
    }
    
    @Test
    public void testExpandCollection() {
        Share kuka = new Share(6000, "kuka");
        ShareItem kukaItem = new ShareItem(kuka, 2);
        bob.getSAcc().expandCollection(bob, kukaItem);
        assertEquals(3, bob.getSAcc().getCollection().length);
        assertEquals(kukaItem, bob.getSAcc().getCollection()[2]);
    }

    @Test
    public void testShortenCollection() {
        bob.getSAcc().shortenCollection(bob, bob.getSAcc().getCollection()[1]);
        assertEquals(bmwItem, bob.getSAcc().getCollection()[0]);
    }

    @Test
    public void testSearch() {
        assertEquals(true, bob.getSAcc().search(bob.getSAcc().getCollection(), vw));
    }

    @Test
    public void testExpandArray() {
        ShareItem[] newArray = bob.getSAcc().expandArray(bob.getSAcc().getCollection());
        assertEquals(bob.getSAcc().getCollection().length + 1, newArray.length);
    }

    @Test
    public void testShortenArray() {
        ShareItem[] newArray = bob.getSAcc().shortenArray(bob.getSAcc().getCollection());
        assertEquals(bob.getSAcc().getCollection().length - 1, newArray.length);
    }

    @Test
    public void testCopyMore() {
        Share kuka = new Share(1552, "kuka");
        ShareItem kukaItem = new ShareItem(kuka, 20);
        ShareItem[] newArray = new ShareItem[bob.getSAcc().getCollection().length + 1];
        newArray = bob.getSAcc().copyMore(bob.getSAcc().getCollection(), newArray, kukaItem);
        bob.getSAcc().setCollection(newArray);
        for(int i = 0; i < bob.getSAcc().getCollection().length; i++)
            System.out.println(bob.getSAcc().getCollection()[i].getName());
        assertEquals(bob.getSAcc().getCollection()[2], kukaItem);        
    }

    @Test
    public void testCopyLess() {
        ShareItem[] newArray = new ShareItem[bob.getSAcc().getCollection().length - 1];
        newArray = bob.getSAcc().copyLess(bob.getSAcc().getCollection(), newArray, vwItem);
        bob.getSAcc().setCollection(newArray);
        assertEquals(bob.getSAcc().getCollection()[0], bmwItem);
        assertEquals(1, bob.getSAcc().getCollection().length);
        
    }

    @Test
    public void testGetName() {
        ShareDepositAccount sAcc = new ShareDepositAccount("Bob");
        assertEquals("Bob", sAcc.getName());
    }

}
