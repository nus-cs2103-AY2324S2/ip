import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("    -----------------------------------");
        System.out.println("    Hello! I'm ByteTalker");
        System.out.println("    What can I do for you?");
        System.out.println("    Supported tasks are todo, deadline, and event");
        System.out.println("    todo {task}");
        System.out.println("    deadline {task} /by {date and time}");
        System.out.println("    event {task} /from {date and time} /to {date and time}");
        System.out.println("    Supported date and time format are yyyy-MM-dd HHmm, and dd/MM/yyyy HHmm");
        System.out.println("    -----------------------------------");
    }

    public void showBye() {
        System.out.println("    -----------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    -----------------------------------");
    }

    public void showErrorMsgStoreTasks() {
        System.out.println("    -----------------------------------");
        System.out.println("    Error while changing the task list");
        System.out.println("    Please try again");
        System.out.println("    -----------------------------------");
    }

    public String storeUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void markTaskMsg(Task task) {
        System.out.println("    -----------------------------------");
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
        System.out.println("    -----------------------------------");
    }

    public void unmarkTaskMsg(Task task) {
        System.out.println("    -----------------------------------");
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
        System.out.println("    -----------------------------------");
    }

    public void deleteTaskMsg(Task task, int length) {
        System.out.println("    -----------------------------------");
        System.out.println("    Got it. I've removed this task:");
        System.out.println("        " + task.toString());
        System.out.println("    Now you have " + length + " task in the list.");
        System.out.println("    -----------------------------------");
    }

    public void addTaskMsg(Task task, int length) {
        System.out.println("    -----------------------------------");
        System.out.println("    Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("    Now you have " + length + " tasks in the list.");
        System.out.println("    -----------------------------------");
    }

    public void showFileNotFoundErrorMsg() {
        System.out.println("File is not found");
    }

    public void returnList(ArrayList<Task> tasks) {
        System.out.println("    -----------------------------------");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println("    -----------------------------------");
    }

    public static void showDateTimeParseErrorMsg(Exception e) {
        System.err.println("Unable to parse the date and time string: " + e.getMessage());
    }
}
