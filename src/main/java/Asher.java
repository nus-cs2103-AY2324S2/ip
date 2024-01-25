import java.util.Scanner;

public class Asher {
    public String name;
    private static String[] userCommands = new String[100];
    private static int count = 0;

    public static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echoCommand(String command) {
        System.out.println("added:" + " " + command);
    }

    public static boolean isExitCommand(String command) {
        return command.toLowerCase().equals("bye");
    }

    public static void addCommand(String command) {
        if (count < userCommands.length) {
            userCommands[count] = command;
            count++;
        } else {
            System.out.println("Task is full, unable to add more");
        }
    }

    public static void displayList() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + " " + userCommands[i]);
        }
    }

    public static void processCommand(String command) {
        if (isExitCommand(command)) {
            Asher.exit();
        } else if (command.equals("list")) {
            displayList();
        } else {
            addCommand(command);
            echoCommand(command);
        }
    }

    public static void main(String[] args) {
        Asher.greet();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            Asher.processCommand(command);

        } while (!Asher.isExitCommand(command));
    }
}
