import java.util.Scanner;
import java.util.ArrayList;

public class Liv {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static void displayBar() {
        System.out.println("____________________________________________________________");
    }
    private static void handleGreetCommand() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }
    private static void handleByeCommand() {
        System.out.println("Farewell, see you next time!");
    }

    private static int parseNumberInCommand(String command) {
        // Expect input in this form: "<command> <number>"
        int spaceIndex = command.indexOf(' ');
        String numberString = command.substring(spaceIndex + 1);
        return Integer.parseInt(numberString);
    }
    private static void handleListCommand() {
        // display task
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int displayedIndex = i + 1;
            System.out.println(displayedIndex + ". " + task.getStatusIcon() + " " + task.getDescription());
            //System.out.printf("%d. %s %s\n", displayedIndex, task.getStatusIcon(), task.getDescription());
        }
    }

    private static void handleMarkOrUnmarkCommand(String command, boolean isMark) {
        int trueIndex = parseNumberInCommand(command) - 1;
        Task task = tasks.get(trueIndex);
        if (isMark) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }
    }
    private static void addNewTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("added: " + description);
    }

    public static void main(String[] args) {
        handleGreetCommand();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            displayBar();
            if (input.equalsIgnoreCase("bye")) {
                handleByeCommand();
                displayBar();
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                handleListCommand();
            } else if (input.startsWith("mark")) {
                handleMarkOrUnmarkCommand(input, true);
            } else if (input.startsWith("unmark")) {
                handleMarkOrUnmarkCommand(input, false);
            } else {
                addNewTask(input);
            }
            displayBar();
        }
    }
}


