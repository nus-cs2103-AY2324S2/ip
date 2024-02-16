package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke program.
 */
public class Ui {

    private Scanner inputs = new Scanner(System.in);

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        return inputs.nextLine();
    }


    public String welcomeMessage() {
        StringBuilder result = new StringBuilder();
        result.append("     Hello! I'm TALKTOMEORILLDIE\n");
        result.append("     What can I do for you?\n");
        return result.toString();
    }

    /**
     * Generates the goodbye message.
     * @return The goodbye message.
     */
    public String goodbyeMessage() {
        return "     Bye. Hope to see you again soon!";
    }

    /**
     * Shows the task marked as done message.
     */
    public String showMarkedAsDone(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("     Nice! I've marked this task as done:\n");
        result.append("     ").append(task).append("\n");
        return result.toString();
    }

    /**
     * Shows the task marked as not done message.
     */
    public String showMarkedAsNotDone(Task task) {
        StringBuilder result = new StringBuilder();
        result.append("     Nice! I've marked this task as not done:\n");
        result.append("     ").append(task).append("\n");
        return result.toString();
    }

    /**
     * Shows the added task message.
     */
    public String showAddedTask(Task task, int taskNum) {
        StringBuilder result = new StringBuilder();
        result.append("     Got it. I've added this task:\n");
        result.append("     ").append(task).append("\n");
        result.append("     Now you have ").append(taskNum).append(" tasks in the list.\n");
        return result.toString();
    }

    /**
     * Shows the deleted task message.
     */
    public String showDeleteTask(Task task, int taskNum) {
        StringBuilder result = new StringBuilder();
        result.append("     Noted. I've removed this task:\n");
        result.append("     ").append(task).append("\n");
        result.append("     Now you have ").append(taskNum + 1).append(" tasks in the list.\n");
        return result.toString();
    }

    /**
     * Shows the tasks on a specific date.
     */
    public String showDeadlinesEventsOnDate(Task[] tasks, int taskNum, LocalDate dateToCheck) {
        StringBuilder result = new StringBuilder();
        result.append("     Deadlines/Events occurring on ")
                .append(dateToCheck.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                .append(":\n");
        for (int i = 0; i < taskNum; i++) {
            if (tasks[i] instanceof Deadline) {
                Deadline deadline = (Deadline) tasks[i];
                if (deadline.getDate().toLocalDate().isEqual(dateToCheck)) {
                    result.append("     ").append(tasks[i].toString()).append("\n");
                }
            }
        }
        return result.toString();
    }

    /**
     * Shows the loading error message.
     */
    public static String showLoadingError() {
        return "     Error: Your file can't be loaded";
    }

    /**
     * Shows the tasks.
     */
    public String showTasks(Task[] tasks, int taskNum) {
        StringBuilder result = new StringBuilder();
        if (taskNum == 0) {
            result.append("     You are doing absolutely nothing, lazy :))\n");
        } else {
            for (int i = 0; i < taskNum; i++) {
                result.append("     ").append(i + 1).append(". ").append(tasks[i]).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Shows the matching tasks.
     */
    public String showMatchingTasks(String keyword) {
        StringBuilder result = new StringBuilder();
        result.append("     Here are the matching tasks in your list:\n");
        int matchCount = 0;
        for (Task task : TaskList.getTasks()) {
            if (task != null && task.description.contains(keyword)) {
                result.append("     ").append(matchCount + 1).append(".").append(task).append("\n");
                matchCount++;
            }
        }
        if (matchCount == 0) {
            result.append("     No matching tasks found.\n");
        }
        return result.toString();
    }


    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        inputs.close();
    }
}
