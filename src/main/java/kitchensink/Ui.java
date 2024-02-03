package kitchensink;

import java.util.Scanner;

import kitchensink.task.Task;

/**
 * In charge of printing statements, to confirm to the user that the action is completed.
 */
public class Ui {
    public static final String LINE = "__________________________________________________________\n";

    /**
     * Processes the user's input into a String.
     * @return User's input in String type.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void welcome() {
        System.out.println(LINE + "Hello! I'm KitchenSink!\n" + "What can I do for you?\n" + LINE);
    }

    public void sayGoodBye() {
        System.out.println(LINE + "Bye. Hope to see you again soon!\n" + LINE);
    }

    /**
     * Confirms to the user that the specified task is added.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being added.
     * @param taskListSize Number of tasks in taskList.
     */
    public void sayTaskAdded(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        System.out.println(Ui.LINE
                + "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n"
                + Ui.LINE);
    }

    public void displayTasks(List taskList) {
        System.out.println(Ui.LINE + "Here are the tasks in your list:\n" + taskList.toString() + "\n" + Ui.LINE);
    }

    public void sayTaskMarked(Task task) {
        System.out.println(Ui.LINE + "Nice! I've marked this task as done:\n" + task.toString() + "\n" + Ui.LINE);
    }

    public void sayTaskUnmarked(Task task) {
        System.out.println(Ui.LINE + "OK, I've marked this task as not done yet:\n" + task.toString() + "\n" + Ui.LINE);
    }

    /**
     * Confirms to the user that the specified task is deleted.
     * Displays the number of tasks remaining and distinguishes between singular and plural form.
     * @param task The task that is being deleted.
     * @param taskListSize Number of tasks in taskList.
     */
    public void sayTaskDeleted(Task task, int taskListSize) {
        String taskOrTasks = taskListSize == 1 ? " task " : " tasks ";
        System.out.println(Ui.LINE + "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + taskListSize + taskOrTasks + "in the list.\n" + Ui.LINE);
    }

    public void displayResults(List results) {
        System.out.println(LINE + "Here are the matching tasks in your list:\n"
                + results.toString() + "\n" + LINE);
    }
}
