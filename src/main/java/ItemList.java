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
        try {
            if (accessCommand.equals("list")) {
                printItems();
                return;
            }
            if (accessCommand.equals("mark") || accessCommand.equals("unmark")) {
                throw new IllegalArgumentException("You need to choose an item number to mark/unmark!");
            }
            if (accessCommand.indexOf("mark ") == 0) {
                int itemNumber = Integer.parseInt(accessCommand.substring(5));
                if (1 <= itemNumber && itemNumber <= items.size()) {
                    markItemAsDone(itemNumber);
                    return;
                }
                throw new IllegalArgumentException("Invalid item number!");
            }
            if (accessCommand.indexOf("unmark ") == 0) {
                int itemNumber = Integer.parseInt(accessCommand.substring(7));
                if (1 <= itemNumber && itemNumber <= items.size()) {
                    markItemAsUndone(itemNumber);
                    return;
                }
                throw new IllegalArgumentException("Invalid item number!");
            }
            if (accessCommand.equals("delete")) {
                throw new IllegalArgumentException("You need to choose an item number to delete!");
            }
            if (accessCommand.indexOf("delete ") == 0) {
                int itemNumber = Integer.parseInt(accessCommand.substring(7));
                deleteItem(itemNumber);
                return;
            }
            addItem(accessCommand);
        } catch (NumberFormatException n) {
            System.out.println(CommandHandler.indentLine("Zzz... The target item must be an integer! Nice try, you won't catch me sleeping :p"));
        } catch (IllegalArgumentException i) {
            String errorMessage = CommandHandler.indentLine(i.getMessage());
            System.out.println(errorMessage + " Nice try, you won't catch me sleeping :p");
        }
    }

    /**
     * Creates and adds an item to this list.
     *
     * @param item Item to be added.
     */
    public void addItem(String item) throws IllegalArgumentException {
        Item createdItem = null;
        if (item.equals("todo") || item.equals("deadline") || item.equals("event")) {
            throw new IllegalArgumentException("You forgot to include the description at all!");
        }
        boolean isToDo = item.indexOf("todo ") == 0;
        boolean isDeadline = item.indexOf("deadline ") == 0;
        boolean isEvent = item.indexOf("event ") == 0;
        if (isToDo) {
            createdItem = new ToDo(item.substring(5));
        } else if (isDeadline) {
            int deadlineIndex = item.indexOf("/by ");
            if (deadlineIndex == -1) {
                throw new IllegalArgumentException("Missing the '/by' field! Try again.");
            }
            String description = item.substring(9, deadlineIndex - 1);
            String givenDeadline = item.substring(deadlineIndex + 4);
            createdItem = new Deadline(description, givenDeadline);
        }
        else if (isEvent) {
            int fromIndex = item.indexOf("/from ");
            int toIndex = item.indexOf("/to ");
            if (fromIndex == -1) {
                throw new IllegalArgumentException("Missing the '/from' field! Try again.");
            }
            if (toIndex == -1) {
                throw new IllegalArgumentException("Missing the '/to' field! Try again.");
            }
            if (fromIndex >= toIndex) {
                throw new IllegalArgumentException("Your '/from' field must be before your '/to' field! Try again.");
            }
            String description = item.substring(6, fromIndex - 1);
            String start = item.substring(fromIndex + 6, toIndex - 1);
            String end = item.substring(toIndex + 4);
            createdItem = new Event(description, start, end);
        } else {
            throw new IllegalArgumentException("This is not a task!");
        }
        items.add(createdItem);
        System.out.println(CommandHandler.indentLine("added: " + createdItem.getDescription()));
    }

    /**
     * Deletes an item from this list.
     *
     * @param itemNumber Item number to be deleted.
     */
    public void deleteItem(int itemNumber) throws IllegalArgumentException {
        if (itemNumber <= 0 || itemNumber > items.size()) {
            throw new IllegalArgumentException("Invalid item number!");
        }
        Item removedItem = items.remove(itemNumber - 1);
        System.out.println(CommandHandler.indentLine("Noted. I've removed this task:"));
        System.out.println(CommandHandler.indentLine("  " + removedItem.getDescription()));
        System.out.println(CommandHandler.indentLine(String.format("Now you have %d task(s) in the list.", items.size())));
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
