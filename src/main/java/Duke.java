

public class Duke {
    static String horzLine = "____________________________________________________________";
    static String chatbotName = "Destiny";
    static String greetingMessage = horzLine
            + "\nGreetings! I'm " + chatbotName + "\nHow may I serve you?\n"
            + horzLine;
    static String goodbyeMessage = horzLine +
            "\nBye. Hope to see you again soon!\n"
            + horzLine;

    static String[] textStorage = new String[100];
    static int numInStorage = 0;

    public static void echo(String message) {
        textStorage[numInStorage] = message;
        numInStorage++;
        System.out.println(horzLine);
        System.out.println("added: " + message);
        System.out.println(horzLine);
    }
    public static void list() {
        System.out.println(horzLine);
        for (int i = 0; i < numInStorage; i++) {
            System.out.println(i + ". " + textStorage[i]);
        }
        System.out.println(horzLine);
    }

    public static void main(String[] args) {
        System.out.println(greetingMessage);

        ChatbotUser user = new ChatbotUser();

        while(!user.getUserInput().equalsIgnoreCase("bye")) {
            user.inputMessage();
            if (user.getUserInput().equalsIgnoreCase("list")) {
                list();
            } else if (!user.getUserInput().equalsIgnoreCase("bye")) {
                echo(user.getUserInput());
            }
        }
        System.out.println(goodbyeMessage);

    }
}
