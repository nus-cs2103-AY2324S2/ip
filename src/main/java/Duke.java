import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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
        List<String> tasks = new ArrayList<String>();
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ending(horizontalLine);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < tasks.size(); i++) {
                    int j = i+1;
                    System.out.println(j + ". " + tasks.get(i));
                }
                System.out.println(horizontalLine);
                continue;
            }
            tasks.add(input);
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
        System.out.println("added: " + command);
        System.out.println(horizontalLine);
    }
    public static void ending(String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
