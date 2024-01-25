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
        this.addItem(accessCommand);
    }

    /**
     * Creates and adds an item to this list.
     *
     * @param item Item to be added.
     */
    public void addItem(String item) {
        Item createdItem = null;
        if (item.length() >= 6 && item.substring(0, 5).equals("todo ")) {
            createdItem = new ToDo(item.substring(5));
        } else if (item.length() >= 15 && item.substring(0, 9).equals("deadline ")) {
            int deadlineIndex = item.indexOf("/by ");
            String description = item.substring(9, deadlineIndex);
            String givenDeadline = item.substring(deadlineIndex + 4);
            createdItem = new Deadline(description, givenDeadline);
        }
        else if (item.length() >= 19 && item.substring(0, 6).equals("event ")) {
            int fromIndex = item.indexOf("/from ");
            int toIndex = item.indexOf("/to ");
            String description = item.substring(6, fromIndex);
            String start = item.substring(fromIndex + 6, toIndex);
            String end = item.substring(toIndex + 4);
            createdItem = new Event(description, start, end);
        }
        items.add(createdItem);
        System.out.println(CommandHandler.indentLine("added: " + createdItem.getDescription()));
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
