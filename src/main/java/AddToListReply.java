public class AddToListReply extends Reply {
    public AddToListReply(String task, int currSize) {
        super("Ah-ah-ah! I have added ONE task:\n" +
                "        " + task + "\n" +
                "      You have one, two... " + currSize + " task(s)!");
    }
}
