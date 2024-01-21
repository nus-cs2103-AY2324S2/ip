/**
 * ChatBot encapsulates the behaviour of a Chat Bot.
 *
 * @author Titus Chew
 */
public class ChatBot {
    private final String chatBotName;

    public ChatBot(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    /**
     * Greets the user when entering the application.
     */
    public void greet() {
        insertLine();
        System.out.printf(
                "Hello! I'm %s!\n" +
                "What can I do for you?\n",
                chatBotName);
    }

    /**
     * Greets the user when exiting the application.
     */
    public void exit() {
        insertLine();
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Inserts a horizontal line.
     */
    private void insertLine() {
        System.out.println("____________________________________________________________");
    }

}
