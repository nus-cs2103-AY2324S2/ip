import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Campus {
    static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Campus.greet();

        String userInput;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
            if (Objects.equals(userInput, "bye")) {
                scanner.close();
                break;
            } else if (Objects.equals(userInput, "list")) {
                Campus.display();
            } else {
                Campus.add(userInput);
            }
        }

        Campus.exit();
    }

    public static void display() {
        int numOfTasks = Campus.tasks.size();
        String listOfTasks = "";
        for (int i = 0; i < numOfTasks; i++) {
            int listNum = i + 1;
            String task = Campus.tasks.get(i);
            listOfTasks += String.format("%s. %s\n", listNum, task);
        }

        String message = "------------------------------\n"
                + String.format("%s\n", listOfTasks)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void add(String input) {
        Campus.tasks.add(input);

        String message = "------------------------------\n"
                + String.format("added: %s\n", input)
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void greet() {
        String message = "------------------------------\n"
                + "Hello! I'm Campus\n"
                + "What can I do for you?\n"
                + "\n"
                + "------------------------------\n";

        System.out.println(message);
    }

    public static void exit() {
        String message = "------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "\n"
                + "------------------------------\n";
        System.out.println(message);
    }
}
