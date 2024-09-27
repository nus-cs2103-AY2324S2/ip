package yoda.components;

public class Replies {
    private final String chatbotName;

    /**
     * Constructor for Replies.
     * @param chatbotName The name of the chatbot.
     */
    public Replies(String chatbotName) {
        this.chatbotName = chatbotName;
    }

    /**
     * Prints a message wrapped with lines for better readability.
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Prints a line for visual separation in the console output.
     */
    private void printLine() {
        System.out.println("________________________________________________________");
    }

    /**
     * Prints a greeting message when the chatbot starts.
     */
    public void printGreeting() {
        printLine();
        System.out.println("Greetings! " + this.chatbotName + ", I am\nAssist you, may I?");
        printLine();
    }
}
