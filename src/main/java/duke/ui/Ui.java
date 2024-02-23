package duke.ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.task.Task;
import duke.tasklist.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Handles the user interface of the Duke application. It is responsible for
 * all the interactions with the user, including taking input and showing
 * messages to the user.
 */
public class Ui {
    private Scanner scanner;
    private final String break_line = "_____________________________________________________\n";

    /**
     * Initializes a Ui instance with a new Scanner object to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     *
     * @return Welcome message
     */
    public String showHello() {
        return break_line
                + " Hello! I'm Your Only Friend\n"
                + " What can I do for you?\n"
                + "\n"
                + " If you need to know what commands to use:\n"
                + " Write 'help' and Your Only Friend will help you out!\n"
                + break_line;
    }

    /**
     * Displays the goodbye message to the user.
     *
     * @return goodbye message
     */
    public String showBye() {
        return break_line
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + break_line;
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     * @return error message
     */
    public String showError(String message) {
        return break_line + message + break_line;
    }

    /**
     * Reads a command from the user.
     * @return The user's command as a trimmed, lowercase string.
     */
    public String readCommand() {
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Displays the list of tasks to the user.
     * @param tasks The TaskList containing the tasks to be displayed.
     * @return String representation of the list of tasks
     */
    public String showList(TaskList tasks) {
        String str1 = break_line + " Here are the tasks in your list:\n";
        String str2 = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            str2 += " " + i + "." + tasks.getTasks().get(i - 1).toString() + "\n";
        }
        String str_out = str1 + str2 + break_line;
        return str_out;
    }

    /**
     * Displays a specific task to the user along with a custom message.
     * @param msg The message to be displayed above the task.
     * @param task The task to be displayed.
     * @return String representation of task
     */
    public String showTask(String msg, Task task) {
        return break_line + msg + "\n"
                + task.toString()
                + break_line;
    }

    /**
     * Displays a specific task to the user along with a custom message, and
     * shows the total number of tasks in the list.
     *
     * @param msg   The message to be displayed above the task.
     * @param task  The task to be displayed.
     * @param tasks The TaskList to count the total number of tasks from.
     * @return task and total tasks' string representation
     */
    public String showTaskWithNum(String msg, Task task, TaskList tasks) {
        return break_line + msg + "\n" + task.toString() + "\n"
                + "Now you have " + tasks.getSize()
                + (tasks.getSize() <= 1 ? " task in the list." : " tasks in the list.\n")
                + break_line;
    }

    /**
     * Displays list of tasks which includes the keyword for "finding tasks".
     * @param tasks
     * @return String representation of tasks with the keyword
     */
    public String showMatchingList(TaskList tasks) {
        String str = "";
        for (int i = 1; i <= tasks.getSize(); i++) {
            str += " " + i + "." + tasks.getTasks().get(i - 1).toString() + "\n";
        }
        return break_line + " Here are the matching tasks in your list:\n" + str + break_line;
    }

    /**
     * Displays list of commands to help out user
     * @return A list of commands and what they do.
     */
    public String showHelp() {
        return " Small Guide: \n"
                + " 'bye'       : exits the application \n"
                + " 'deadline'  : to create deadline task, format = 'deadline description /by yyyy-MM-dd HH:mm'\n"
                + " 'delete'    : to remove a task at a certain index, format = 'delete index'\n"
                + " 'event'     : to create event task, format = 'event description /from yyyy-MM-dd HH:mm "
                + "/to yyyy-MM-dd HH:mm'\n"
                + " 'find'      : to search for a keyword in task descriptions, format = 'find keyword'\n"
                + " 'mark'      : to mark a task at a certain index as complete, format = 'mark index'\n"
                + " 'todo'      : to create a todo task with no particular time constraint, format = 'todo description'\n"
                + " 'unmark'    : to unmark a task at a certain index as incomplete, format = 'unmark index'\n";
    }

    /**
     * Displays help in a new window.
     */
    public void showHelpPopup() {
        String helpText = showHelp();
        TextArea textArea = new TextArea(helpText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.setScene(new Scene(textArea, 675, 200));
        helpStage.show();
    }
}

