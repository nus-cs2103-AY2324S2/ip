package talktomeorilldie;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the user interface of the TalkToMeOrIllDie program.
 */
public class Ui {

    private final Scanner inputs = new Scanner(System.in);

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        return inputs.nextLine();
    }

    /**
     * Shows the welcome message.
     * @return The welcome message.
     */
    public String welcomeMessage() {
        return "     Hello! I'm TalkToMeOrIllDie\n"
                + "     What can I do for you?\n";
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
     * @param task The task marked as done.
     * @return The task marked as done message.
     */
    public String showMarkedAsDone(Task task) {
        return "     Nice! I've marked this task as done:\n"
                + "     " + task + "\n";
    }

    /**
     * Shows the task marked as not done message.
     * @param task The task marked as not done.
     * @return The task marked as not done message.
     */
    public String showMarkedAsNotDone(Task task) {
        return "     Nice! I've marked this task as not done:\n"
                + "     " + task + "\n";
    }

    /**
     * Shows the added task message.
     * @param task The added task.
     * @param taskNum The number of tasks.
     * @return The added task message.
     */
    public String showAddedTask(Task task, int taskNum) {
        return "     Got it. I've added this task:\n"
                + "     " + task + "\n"
                + "     Now you have " + taskNum + " tasks in the list.\n";
    }

    /**
     * Shows the deleted task message.
     * @param task The deleted task.
     * @return The deleted task message.
     */
    public String showDeleteTask(Task task) {
        return "     Noted. I've removed this task:\n"
                + "     " + task + "\n"
                + "     Now you have " + (TaskList.getTaskNum() - 1) + " tasks in the list.\n";
    }

    /**
     * Shows the tasks.
     * @param tasks The tasks.
     * @param taskNum The number of tasks.
     * @return The tasks.
     */
    public String showDeadlinesEventsOnDate(Task[] tasks, int taskNum, LocalDate dateToCheck) {
        StringBuilder result = new StringBuilder();
        result.append("     Deadlines occurring on ")
                .append(dateToCheck.format(DateTimeFormatter.ofPattern("d/M/yyyy")))
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
     * Shows the tasks.
     * @param tasks The tasks.
     * @param taskNum The number of tasks.
     * @return The tasks.
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
     * @param matchingTasks The keyword to match.
     * @return The matching tasks.
     */
    public String showMatchingTasks(String matchingTasks) {
        return "     Here are the matching tasks in your list:\n"
                + matchingTasks;
    }


    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        inputs.close();
    }

    /**
     * Shows the help message.
     * @return The help message.
     */
    public String showHelp() {
        return "     Here are the commands you can use:\n"
                + "     1. todo <description> \n             - Adds a todo task\n\n"
                + "     2. deadline <description> /by <date in dd/mm/yyyy> <time> \n"
                + "             - Adds a deadline task\n\n"
                + "     3. event <description> /from <day NOT DATE> <time> /to <time> \n"
                + "             - Adds an event task\n\n"
                + "     4. list \n             - Lists all tasks\n\n"
                + "     5. mark <task number> \n             - Marks a task as done\n\n"
                + "     6. unmark <task number> \n             - Unmasks a task as not done\n\n"
                + "     7. delete <task number> \n             - Deletes a task\n\n"
                + "     8. find <keyword> \n             - Finds tasks with the keyword\n\n"
                + "     9. on <date dd/mm/yyyy> \n"
                + "             - Finds tasks with the matching date\n\n"
                + "     10. bye \n             - Exits the program\n";
    }
}
