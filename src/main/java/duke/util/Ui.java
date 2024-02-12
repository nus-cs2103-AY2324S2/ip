package duke.util;

import java.io.InputStream;
import java.util.Scanner;

import duke.task.Task;


/**
 * Represents the mechanism of a user interface.
 */
public class Ui {
    private static final String LINE = "\t________________________________________________________________";

    private String name;
    private String logo;
    private Scanner scanner;

    /**
     * Constructs a UI object.
     *
     * @param name
     * @param logo
     * @param inputStream
     */
    public Ui(String name, String logo, InputStream inputStream) {
        this.name = name;
        this.logo = logo;
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm " + this.name + "." + "\nWhat can I do for you?";
    }

    /**
     * Reads command using Scanner.
     *
     * @return String read from InputStream.
     */
    public String readCommand() {
        System.out.println();
        return scanner.nextLine();
    }

    /**
     * Displays line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns error message.
     *
     * @param errorMessage Details about the error to be displayed.
     * @return Error message.
     */
    public String showError(String errorMessage) {
        return "OOPS!!! " + errorMessage;
    }

    /**
     * Returns error message due to reading from file.
     *
     * @return Error message.
     */
    public String showLoadingError() {
        return "OOPS!!! Error loading tasks from file.";
    }

    /**
     * Returns notification on successful mark operation.
     *
     * @param task Task that was marked as done.
     * @return Success message.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:" + "\n\t" + task.printTask();
    }

    /**
     * Returns notification on successful unmark operation.
     *
     * @param task Task that was marked as not done.
     * @return Success message.
     */
    public String showUnmarked(Task task) {
        return "Ok, I've marked this task as not done yet:" + "\n\t" + task.printTask();
    }

    /**
     * Returns notification on successful add operation.
     *
     * @param t Task that was added.
     * @param list Holds all tasks added.
     * @return Success message.
     */
    public String showAdded(Task t, TaskList list) {
        int size = list.getSize();
        return "Got it! I've added this task:\n\t" + t.printTask()
                + "\nNow you have " + size + (size > 1 ? " tasks" : " task") + " in the list.";
    }

    /**
     * Returns all tasks in list.
     * Outputs message if list is empty.
     *
     * @param list Holds all tasks added
     * @return String of tasks.
     */
    public String showTasks(TaskList list) {
        if (list.getSize() == 0) {
            return "OOPS!!! No task in list." + "\nYou may add task with keywords: todo, deadline, event.";
        }
        return list.print();
    }

    /**
     * Returns notification on successful delete operation.
     *
     * @param t Task that was deleted.
     * @param list Holds all tasks remaining.
     * @return Success message.
     */
    public String showDeleted(Task t, TaskList list) {
        String multiplicity = list.getSize() > 1 ? " tasks" : " task";
        return "Noted I've removed this task:" + "\n\t" + t.printTask()
                + "\nNow you have " + list.getSize() + multiplicity + " in the list.";
    }


    /**
     * Returns exit message.
     *
     * @return Exit message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns all tasks in list.
     *
     * @param list Holds all tasks that matches search.
     * @return String of tasks.
     */
    public String showMatchedTasks(TaskList list) {
        if (list.getSize() == 0) {
            return "OOPS!!! No task matches you search.";
        }
        return list.printFound();
    }
}
