import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Sleepy";
        String welcomeLine = "Hello! I'm " + name;
        String questionLine = "What can I do for you?";

        System.out.println(welcomeLine);
        System.out.println(questionLine);
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextUserCommand = userInput.nextLine();
            if (nextUserCommand.equals("bye")) {
                CommandHandler.exit();
                break;
            }
            CommandHandler.echoCommand(nextUserCommand);
        }
    }
}
