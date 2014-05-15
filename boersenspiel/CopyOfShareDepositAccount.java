package boersenspiel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyOfShareDepositAccount extends Asset {
    private ShareItem[] collection = new ShareItem[0];
    private List<ShareItem> list = new ArrayList<ShareItem>();
    
    

    public CopyOfShareDepositAccount(String name) {
        this.setName(name);

    }
    
    public long getValue(){
        long value = 0;
        for(int index = 0; index < this.getCollection().length; index++){
            value += getCollection()[index].getValue();
        }
        
        return value;
    }

    public ShareItem[] getCollection() {
        return this.collection;
    }

    public void setCollection(ShareItem[] newCollection) {
        this.collection = newCollection;
    }

    public void setCollection(int index, ShareItem item){
        this.collection[index].setSAmount(item.getSAmount());
        this.collection[index].setValue(item.getValue());
    }
    
    
    
    public String toString() {
        return "Name: " + getName() + ", Gesamteinkaufswert: " + (double)this.getValue()/100 + "€.";
    }

    /**
     * Durchsucht ein Array nach einem Aktiennamen und gibt die Stelle im Array aus, wenn er
     * gefunden wird
     */
    public boolean search(ShareItem[] collection, Share toCompare) {

        if (collection.length > 0) {
            for (int index = 0; index < collection.length; index++) {
                if (collection[index].getName() == toCompare.getName()) {
                    return true;
                }
            }
        }
        return false;
    }
    

    
    
    
    

    public void add(ShareItem item){
        list.add(item);
    }
    
    public void remove(ShareItem item){
        list.remove(item);
    }
    
    public List  getList(){
        return this.list;
    }
    
    
    
    
    public static void main(String args[]){
        CopyOfShareDepositAccount acc = new CopyOfShareDepositAccount("bob");
        
        Share bmw = new Share(2000, "bmw");
        Share vw = new Share(3000, "vw");
        Share kuka = new Share(1000, "kuka");
        
        ShareItem bmwItem = new ShareItem(bmw, 20);
        ShareItem vwItem = new ShareItem(vw, 20);
        ShareItem kukaItem = new ShareItem(kuka, 20);

        acc.add(bmwItem);
        acc.add(vwItem);
        acc.add(kukaItem);
//        acc.remove(vwItem);
        
        Iterator<ShareItem> iterator = acc.list.iterator();
        while(iterator.hasNext())
        System.out.println( iterator.next() );
    }
    
    
}