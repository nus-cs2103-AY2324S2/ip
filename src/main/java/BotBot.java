import java.util.Scanner;

public class BotBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        TaskList list = new TaskList();
        BotBot.greet();

        while (scanner.hasNext()) {
            String nextTask = scanner.next();

            // Exit sequence
            if (nextTask.equals("bye")) {
                BotBot.exit();
                break;
            }

            // Main functionality
            BotBot.divider();
            list.addTask(nextTask);
            BotBot.divider();
            list.printList();
            BotBot.divider();

        }

        scanner.close();
    }

    // Print functionalities
    private static void print(String string) {
        System.out.println(string);
    }
    private static void divider() {
        BotBot.print("########################################");
    }
    public static void greet() {
        BotBot.divider();
        BotBot.print("I am BotBot!\nWhat can I do for you?");
        BotBot.divider();
    }
    public static void exit() {
        BotBot.print("Goodbye! See you soon!");
        BotBot.divider();
    }

}
