import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void drawLine() {
        /**
         * Print out a line on the screen
         */
        System.out.println("-----------------------------------------------------");
    }

    public static void addTask(String task, List<String> storage) {
        /**
         * Add input task into storage
         */
        storage.add(task);
        drawLine();
        System.out.println("Added: " + task);
        drawLine();
    }

    public static void listTask(List<String> storage) {
        drawLine();
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        drawLine();
    }

    public static void startChat() {
        /**
         * Provide commands to communicate with chatbot
         */
        drawLine();
        System.out.println("Hello! I'm Colin");
        System.out.println("What can I do for you?");
        drawLine();
        Scanner scanner = new Scanner(System.in);
        List<String> storage = new ArrayList<>();

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                drawLine();
                System.out.println("Bye. Hope to see you again soon!");
                drawLine();
                break;
            } else if (command.equals("list")) {
                listTask(storage);
            } else {
                addTask(command, storage);
            }
        }
    }


    public static void main(String[] args) {
        startChat();
    }
}
