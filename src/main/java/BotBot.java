import java.util.Scanner;

public class BotBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        TaskList list = new TaskList();
        BotBot.greet();

        while (scanner.hasNext()) {
            String nextTask = scanner.next();
            BotBot.divider();
            // Logic sequence
            if (nextTask.equals("bye")) {
                BotBot.exit();
                break;
            } else if (nextTask.startsWith("mark ")) {
                System.out.println("Good job on completing the task:");
                list.mark(Integer.parseInt(nextTask.substring(5)));
            } else if (nextTask.startsWith("unmark ")) {
                System.out.println("I have unmarked a task:");
                list.unmark(Integer.parseInt(nextTask.substring(7)));
            } else {
                list.addTask(nextTask);
            }
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
