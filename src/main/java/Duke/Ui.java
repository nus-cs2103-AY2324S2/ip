package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke program.
 */
public class Ui {
    Scanner inputs = new Scanner(System.in);

    /**
     * Shows the welcome message.
     */
    public void welcomeMessage () {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm TALKTOMEORILLDIE");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the goodbye message.
     */
    public void goodbyeMessage () {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the task marked as done message.
     */
    public void showMarkedAsDone (Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the task marked as not done message.
     */
    public void showMarkedAsNotDone (Task task){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as not done:");
        System.out.println("       " + task);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the added task message.
     */
    public void showAddedTask (Task task,int taskNum){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + (taskNum) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the deleted task message.
     */
    public void showDeleteTask (Task task,int taskNum){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + (taskNum + 1) + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the tasks on a specific date.
     */
    public void showDeadlinesEventsOnDate (Task[]tasks, int taskNum, LocalDate dateToCheck){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Deadlines/Events occurring on " +
                dateToCheck.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
        for (int i = 0; i < taskNum; i++) {
            if (tasks[i] instanceof Deadline) {
                Deadline deadline = (Deadline) tasks[i];
                if (deadline.getDate().toLocalDate().isEqual(dateToCheck)) {
                    System.out.println("       " + tasks[i].toString());
                }
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the loading error message.
     */
    public static void showLoadingError () {
        System.out.println("Error: Your file can't be loaded");
    }

    /**
     * Shows the tasks.
     */
    public void showTasks(Task[] task,int taskNum) {
        System.out.println("    ____________________________________________________________");
        if (taskNum == 0) {
            System.out.println("     You are doing absolutely nothing, lazy :))");
        } else {
            for (int i = 0; i < taskNum; i++) {
                System.out.println("     " + (i + 1) + ". " + task[i]);
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows the matching tasks.
     */
    public void showMatchingTasks(String keyword) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        int matchCount = 0;
        for (Task task : TaskList.getTasks()) {
            if (task != null && task.description.contains(keyword)) {
                System.out.println("     " + (matchCount + 1) + "." + task);
                matchCount++;
            }
        }
        if (matchCount == 0) {
            System.out.println("     No matching tasks found.");
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        return inputs.nextLine();
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        inputs.close();
    }
}