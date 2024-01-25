public class Chitty {
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s \nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String SPACING = "---------------------------------------------------\n";

    public static void main(String[] args) {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING + GOODBYE_MESSAGE + SPACING);
    }
}
