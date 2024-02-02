package thecount.ui;

public class RemoveFromListReply extends Reply {
    public RemoveFromListReply(String task, int currSize) {
        super("Ah-ah-ah! I have removed ONE the_count.task:\n" +
                "        " + task + "\n" +
                "      You have one, two... " + currSize + " the_count.task(s)!");
    }
}
