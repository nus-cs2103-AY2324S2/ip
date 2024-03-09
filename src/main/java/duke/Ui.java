package duke;

import duke.task.Task;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String name = "Detective";
    private static final String line = "---------------------------------------------";

    private final Scanner in = new Scanner(System.in);

    /**
     * Reads the text entered by the user.
     *
     * @return command (full line) entered by the user
     */
    public String getCommand() {
        return in.nextLine();
    }

    public String showWelcome() {
        return showToUser("Hello! I'm [" + name + "]", "What can I do for you? ovo");
    }

    public String showBye() {
        return showToUser("Bye. Hope to see you again soon! >v<");
    }

    public String showHelp() {
        return showToUser("You can choose the following commands:",
                "bye", "list", "undo", "mark", "unmark", "todo", "deadline", "event", "delete", "find");
    }

    public String showList(TaskList taskList) {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + "\n" + (i + 1) + "." + taskList.get(i);
        }
        return output;
    }

    public String showUnDo(String input) {
        return showToUser("Undo the command:", input);
    }

    public String showMark(TaskList taskList, int taskNum) {
        return showToUser("Nice! I've marked this task as done:", taskList.get(taskNum).toString());
    }

    public String showUnmark(TaskList taskList, int taskNum) {
        return showToUser("Nice! I've unmarked this task as done:", taskList.get(taskNum).toString());
    }

    public String showNoChange(TaskList taskList, int taskNum) {
        return showToUser("No change in the task:", taskList.get(taskNum).toString());
    }

    public String showAddTask(TaskList taskList, Task task) {
        return showToUser("Got it. I've added this task:",
                task.toString(),
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    public String showDeleteTask(TaskList taskList, Task task) {
        return showToUser("Got it. I've deleted this task:",
                task.toString(),
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    public String showFindTask(TaskList taskList) {
        String output = "Here are the matching tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            output = output + "\n" + (i + 1) + "." + taskList.get(i);
        }
        return output;
    }

    public String showError(String message) {
        return showToUser("Error: " + message);
    }

    /**
     * Shows message(s) to the user
     */
    public String showToUser(String... message) {
        String output = line;
        for (String m : message) {
            output = output + "\n" + m;
        }
        return output;
    }
}
