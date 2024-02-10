import java.util.Scanner;
import java.util.List;

public class Ui {
    private static final String LINE = "-------------------------------------------------\n";
    public static String NAME = "Whisper";
    public void showWelcomeMsg() {
        System.out.println("Hello! I'm " + NAME + " , your personal chatbot!\n" +
                "What can I do for you?\n");
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public String inputCommand() {
        System.out.println("Enter your input: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void printLoadFileError() {
        printError("Error loading the file.");
    }

    public void printTasks(List<Task> tasks) {
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }

    public void printTaskAdded(Task task, int totalTask) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTask + " tasks in the list.");
        printLine();
    }

    public void printTaskAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
        printLine();
    }

    public void printTaskAsUndone(Task task) {
        System.out.println("Nice! I've marked this task as not done:\n" + task);
        printLine();
    }

    public void printRemovedTask(Task task, int totalTasks) {
        System.out.println("Noted! I've removed this task: \n" + task);
        System.out.println("Now you have" + totalTasks + " tasks in the list.");
        printLine();
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}