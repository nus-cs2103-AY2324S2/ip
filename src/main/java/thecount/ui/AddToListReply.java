package thecount.ui;

/**
 * A specialized reply class for adding tasks to a list.
 */
public class AddToListReply extends Reply {

    /**
     * Constructs an AddToListReply object.
     *
     * @param task The task that has been added.
     * @param currSize The current size of the task list after addition.
     */
    public AddToListReply(String task, int currSize) {
        super("Ah-ah-ah! I have added ONE task:\n"
                + "" + task + "\n"
                + "You have one, two... " + currSize + " task(s)!");
    }
}
