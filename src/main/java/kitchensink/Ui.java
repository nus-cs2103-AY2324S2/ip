package kitchensink;

import java.util.Scanner;

import kitchensink.task.Task;

/**
 * In charge of printing statements, to confirm to the user that the action is completed.
 */
public class Ui {
    public static final String LINE = "________________________________________________________\n";

//    /**
//     * Processes the user's input into a String.
//     * @return User's input in String type.
//     */
//    public String readInput() {
//        Scanner sc = new Scanner(System.in);
//        String input = sc.nextLine();
//        return input;
//    }

    public String welcome() {
        return (LINE + "Hello! I'm KitchenSink!\n" + "What can I do for you?\n" + LINE);
    }

    public String sayGoodBye() {
        return LINE + "Bye. Hope to see you again soon!\n" + LINE;
    }

    /**
     * Confirms to the user that the specified task is added.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being added.
     * @param taskListSize Number of tasks in taskList.
     * @return String to display in the ui.
     */
    public String sayTaskAdded(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        return (Ui.LINE
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n"
                + Ui.LINE);
    }

    public String displayTasks(List taskList) {
        return Ui.LINE + "Here are the tasks in your list:\n" + taskList.toString() + "\n" + Ui.LINE;
    }

    public String sayTaskMarked(Task task) {
        return (Ui.LINE + "Nice! I've marked this task as done:\n" + task.toString() + "\n" + Ui.LINE);
    }

    public String sayTaskUnmarked(Task task) {
        return (Ui.LINE + "OK, I've marked this task as not done yet:\n" + task.toString() + "\n" + Ui.LINE);
    }

    /**
     * Confirms to the user that the specified task is deleted.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being deleted.
     * @param taskListSize Number of tasks in taskList.
     * @return String to display in the ui.
     */
    public String sayTaskDeleted(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        return (Ui.LINE + "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n" + Ui.LINE);
    }

    public String displayResults(List results) {
        return (LINE + "Here are the matching tasks in your list:\n"
                + results.toString() + "\n" + LINE);
    }
}
