package thecount.ui;

/**
 * A specialized reply class for removing tasks from a list.
 */
public class RemoveFromListReply extends Reply {

    /**
     * Constructs a RemoveFromListReply object.
     *
     * @param task The task that has been removed.
     * @param currSize The current size of the task list after removal.
     */
    public RemoveFromListReply(String task, int currSize) {
        super("Ah-ah-ah! I have removed ONE task:\n"
                + "" + task + "\n"
                + "You have one, two... "
                + currSize
                + " task(s)!");
    }
}
