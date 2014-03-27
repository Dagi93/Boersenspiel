public class ShareDepositAccount extends Asset {
    ShareItem[] collection = new ShareItem[0];

    public ShareDepositAccount(String name) {
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
    public int search(ShareItem[] collection, Share toCompare) {

        if (collection.length > 0) {
            for (int index = 0; index < collection.length; index++) {
                if (collection[index].getName() == toCompare.getName()) {
                    return index;
                }
            }
        }
        return -1;
    }
    



    /** Erstellt ein neues Array, das um einen Index größer ist als das alte */
    public ShareItem[] newArray(ShareItem[] oldArray) {
        ShareItem[] newArray = new ShareItem[oldArray.length + 1];
        return newArray;
    }

    /**
     * Kopiert das alte Array in das neue und fügt an letzter Position das neue
     * ShareItem Objekt ein
     */
    public ShareItem[] copy(ShareItem[] collection, ShareItem[] newArray, ShareItem item) {
        for (int index = 0; index < newArray.length; index++) {
            if (index != newArray.length - 1) {
                newArray[index] = collection[index];
            } else {
                newArray[index] = item;
            }
        }
        return newArray;
    }
}