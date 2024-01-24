import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Lai {
    public static void printDottedLine() {
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<Task>();

        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        printDottedLine();

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.startsWith("mark")) {
                int index = Integer.valueOf(command.replace("mark ", ""));
                Task t = tasks.get(index - 1);
                t.setDone();

                printDottedLine();
                System.out.println("You actually did something? Marked done:");
                System.out.println(t);
                printDottedLine();
            } else if (command.startsWith("unmark")) {
                int index = Integer.valueOf(command.replace("unmark ", ""));
                Task t = tasks.get(index - 1);
                t.setNotDone();

                printDottedLine();
                System.out.println("Come on now, don't be useless. Marked not done:");
                System.out.println(t);
                printDottedLine();
            } else if (!command.equals("list")) {
                tasks.add(new Task(command));
                printDottedLine();
                System.out.println("Added: " + command);
                printDottedLine();
            } else {
                printDottedLine();
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
                }
                printDottedLine();
            }

            System.out.print("> ");
            command = scanner.nextLine();
        }

        System.out.println("---------------------------------------------------------");
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        System.out.println("---------------------------------------------------------");
    }
}
