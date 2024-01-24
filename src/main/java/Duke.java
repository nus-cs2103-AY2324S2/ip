import java.util.Scanner;
public class Duke {
    public static final String CHATBOTNAME = "Sophia";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Greetings greetings = new Greetings();
        Goodbye goodbye = new Goodbye();

        greetings.printCommand("greeting2");

        while (true) {
            String userMessage = scanner.nextLine();

            if (!userMessage.equalsIgnoreCase("bye")) {
                greetings.printCommand(userMessage);
            } else {
                goodbye.printCommand("goodbye2");
                break;
            }
        }

        scanner.close();

    }
}

