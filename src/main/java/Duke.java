import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =  " _____ _               _\n"
                +       "/  __ (_)             | |      \n"
                +       "| /  \\/_  ___ __ _  __| | __ _ \n"
                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";

        System.out.println("Hello from\n" + logo);
        String horizontalLine = "-".repeat(60);
        greeting("Cicada", horizontalLine);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your command and press Enter. Type 'bye' to quit.");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ending(horizontalLine);
                break;
            }
            command(input, horizontalLine);
        }
        scanner.close();

    }

    public static void greeting(String chatbotName, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + chatbotName
                + "\nWhat can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void command(String command, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println(command);
        System.out.println(horizontalLine);
    }
    public static void ending(String horizontalLine) {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
