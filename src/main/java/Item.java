public class Item {
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
        System.out.println(CommandHandler.indentLine("Nice! I've marked this task as done:"));
        System.out.println(CommandHandler.indentLine(this.getDescription()));
    }

    /**
     * Marks this item as undone.
     */
    public void markAsUndone() {
        isDone = false;
        System.out.println(CommandHandler.indentLine("OK, I've marked this task as not done yet:"));
        System.out.println(CommandHandler.indentLine(this.getDescription()));
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