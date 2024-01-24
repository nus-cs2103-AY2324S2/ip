public class Duke {
    public static final String CHATBOTNAME = "Sophia";
    public static void main(String[] args) {

        Greetings greetings = new Greetings();
        Goodbye goodbye = new Goodbye();

        greetings.printDialogue("greeting3");
        goodbye.printDialogue("goodbye3");

    }
}

