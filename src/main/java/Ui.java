import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Ui {
    private static final String LINE = "-------------------------------------------------\n";
    public static String NAME = "Whisper";
    public void showWelcomeMsg() {
        printLine();
        System.out.println("Hello! I'm " + NAME + " , your personal chatbot!\n" +
                "What can I do for you?\n");
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printError(String errorMessage) {
        System.out.println("\nError: " + errorMessage);
    }

    public String inputCommand() {
        System.out.println("Enter your input: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public void printLoadFileError() {
        printError("Error loading the file.");
    }

    public void printTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }

    public void printTaskAdded(Task task, int totalTask) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + totalTask + " tasks in the list.");
        printLine();
    }

    public void printTaskAsDone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        printLine();
    }

    public void printTaskAsUndone(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as not done:\n" + task);
        printLine();
    }

    public void printRemovedTask(Task task, int totalTasks) {
        printLine();
        System.out.println("Noted! I've removed this task: \n" + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }


    public void printExitMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}