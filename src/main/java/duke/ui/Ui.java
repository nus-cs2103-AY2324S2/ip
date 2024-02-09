package duke.ui;

import java.io.PrintStream;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a UI that interacts with users.
 */
public class Ui {
    private static final String LINE = "-----------------------------------------------------------------------";
    private static PrintStream out;

    public static void setOutputStream(PrintStream out) {
        Ui.out = out;
    }

    /**
     * For greeting users at the start.
     * @param name Chatbot name
     */
    public static void greet(String name) {
        out.println(LINE);
        out.println("Heyoo I'm " + name + ".");
        out.println("What can I do for you?");
        out.println(LINE);
    }

    /**
     * For ending the conversation with users.
     * @param tasklist Tasklist with user-specified tasks
     */
    public static void bye(TaskList tasklist) {
        out.println(LINE);
        out.println("Bye lol see you again!");
        out.println(LINE);
        Storage.saveTasks(tasklist);
    }

    /**
     * For echoing messages entered by the users.
     * @param input user message
     */

    public static void echo(String input) {
        out.println(LINE);
        out.println(input);
        out.println(LINE);
    }

    /**
     * For listing out tasks entered by the users
     * @param tasklist Tasklist with user-specified tasks
     */
    public static void listTasks(TaskList tasklist) {
        out.println(LINE);
        out.println("Here are the tasks in your list: ");
        tasklist.listTask();
        out.println(LINE);
    }

    /**
     * For adding tasks entered by the users
     * @param tasklist Tasklist with user-specified tasks
     * @param input Task input
     */
    public static void addTask(TaskList tasklist, String input) {
        out.println(LINE);

        boolean added = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;

        if (added) {
            out.println("Gotcha. I've added this task: ");
            out.println("    " + tasklist.getTaskDescription(tasksCount - 1));
            out.println("Now you have " + tasksCount + " tasks in the list.");
        }
        out.println(LINE);
    }

    /**
     * For updating the status the task from undone to done
     * @param tasklist Tasklist with user-specified tasks
     * @param input user input
     * @param status task status
     */
    public static void mark(TaskList tasklist, String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        tasklist.markTask(num, status);

        out.println(LINE);
        out.println(status ? "Good job on finishing this task: " : "Aw OK, I've marked this task as not done yet: ");
        out.println("    " + tasklist.getTaskDescription(num));
        out.println(LINE);
    }

    /**
     * For deleting a task from the tasklist.
     * @param tasklist Tasklist with user-specified tasks
     * @param input user input
     */
    public static void delete(TaskList tasklist, String input) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        String deletedTask = tasklist.getTaskDescription(num);
        tasklist.deleteTask(num);

        out.println(LINE);
        out.println("Sure, I've removed this task:");
        out.println("    " + deletedTask);
        out.println("Now you have " + TaskList.tasksCount + " tasks in the list.");
        out.println(LINE);
    }

    /**
     * For finding task(s) with a given keyword from the tasklist.
     * @param tasklist Tasklist with user-specified tasks
     * @param keyword
     */
    public static void find(TaskList tasklist, String keyword) {
        out.println(LINE);
        out.println("Here are the matching tasks in your list:");
        tasklist.findTasks(keyword);
        out.println(LINE);
    }
}
