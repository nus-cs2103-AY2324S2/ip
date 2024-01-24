public class RemoveFromListReply extends Reply {
    public RemoveFromListReply(String task, int currSize) {
        super("Ah-ah-ah! I have removed ONE task:\n" +
                "        " + task + "\n" +
                "      You have one, two... " + currSize + " task(s)!");
    }
}
