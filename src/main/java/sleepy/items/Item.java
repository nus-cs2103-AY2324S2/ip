package sleepy.items;

import sleepy.tools.LinePrinter;

/**
 * This class is an abstract class for the items in the list.
 *
 * @author kjw142857
 */
public abstract class Item {
    private String description;
    private boolean isDone = false;

    public Item(String description) {
        this.description = description;
    }

    /**
     * Marks this item as done.
     */
    public void markAsDone() {
        isDone = true;
        LinePrinter.printLine("Nice! I've marked this task as done:");
        LinePrinter.printLine(this.getDescription());
    }

    /**
     * Marks this item as undone.
     */
    public void markAsUndone() {
        isDone = false;
        LinePrinter.printLine("OK, I've marked this task as not done yet:");
        LinePrinter.printLine(this.getDescription());
    }

    /**
     * Returns the description of this item.
     *
     * @return Description of this item.
     */
    public String getDescription() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}