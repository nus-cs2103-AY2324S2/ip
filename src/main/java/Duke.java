import java.util.Scanner;
public class Duke {
    public static final String CHATBOTNAME = "Sophia";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialization of common commands
        Greetings greetings = new Greetings();
        Goodbye goodbye = new Goodbye();
        Tasks tasks = new Tasks();

        greetings.printDialogue("greeting2");

        while (true) {
            String userMessage = scanner.nextLine();

            if (!userMessage.equalsIgnoreCase("bye")) {
                if (userMessage.equalsIgnoreCase("list")){
                    tasks.printTasks();
                    continue;
                }

                tasks.addTasks(userMessage);
                tasks.printDialogue(userMessage);
            } else {
                goodbye.printDialogue("goodbye2");
                break;
            }
        }

        scanner.close();

    }
}

