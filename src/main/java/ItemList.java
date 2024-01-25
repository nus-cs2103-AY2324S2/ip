import java.util.ArrayList;
public class ItemList {
    private ArrayList<Item> items = new ArrayList<>();

    public ItemList() {
    }

    /**
     * Determines the action to be applied on this ItemList according to the access command given.
     *
     * @param accessCommand Access command given.
     */
    public void access(String accessCommand) {
        if (accessCommand.equals("list")) {
            this.printItems();
            return;
        }
        if (accessCommand.length() >= 6 && accessCommand.substring(0, 5).equals("mark ")) {
            try {
                int itemNumber = Integer.parseInt(accessCommand.substring(5));
                if (1 <= itemNumber && itemNumber <= items.size()) {
                    this.markItemAsDone(itemNumber);
                    return;
                }
            } catch (NumberFormatException n) {
            }
        }
        if (accessCommand.length() >= 8 && accessCommand.substring(0, 7).equals("unmark ")) {
            try {
                int itemNumber = Integer.parseInt(accessCommand.substring(7));
                if (1 <= itemNumber && itemNumber <= items.size()) {
                    this.markItemAsUndone(itemNumber);
                    return;
                }
            } catch (NumberFormatException n) {
            }
        }
        Item item = new Item(accessCommand);
        this.addItem(item);
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
     * Marks a specific item in this list as done, and prints its status.
     *
     * @param itemNumber Number (in this list) of the item to be marked.
     */
    public void markItemAsDone(int itemNumber) {
        items.get(itemNumber - 1).markAsDone();
    }

    /**
     * Marks a specific item in this list as undone, and prints its status.
     *
     * @param itemNumber Number (in this list) of the item to be marked.
     */
    public void markItemAsUndone(int itemNumber) {
        items.get(itemNumber - 1).markAsUndone();
    }

    /**
     * Prints out the list of items.
     */
    public void printItems() {
        for (int i = 1; i <= items.size(); i++) {
            Item nextItem = items.get(i - 1);
            System.out.println(CommandHandler.indentLine(i + "." + nextItem.getDescription()));
        }
    }
}
