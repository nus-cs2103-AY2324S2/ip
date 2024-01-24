public class ItemList {
    private Item[] listOfItems = new Item[100];
    private int itemCount = 0;

    public Item[] getList() {
        return this.listOfItems;
    }

    void addToList(String s) {
        listOfItems[itemCount] = new Item(s);
        itemCount++;
    }

    @Override
    public String toString() {
        int c = 0;
        String out = "";
        while (!(listOfItems[c] == null)) {
            out += (c+1) + "." + listOfItems[c].toString() + "\n     ";
            c++;
        }
        return out;
    }
}
