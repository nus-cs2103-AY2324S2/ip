import java.util.ArrayList;
public class ItemList {
    private ArrayList<Item> items = new ArrayList<>();
    public ItemList() {
    }
    /**
     * Adds an item to this list.
     *
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
        System.out.println(CommandHandler.indentLine("added: " + item.getDescription()));
    }
    /**
     * Prints out the list of items.
     */
    public void printItems() {
        for (int i = 1; i <= items.size(); i++) {
            Item nextItem = items.get(i - 1);
            System.out.println(CommandHandler.indentLine(i + ". " + nextItem.getDescription()));
        }
    }
}
