package ChatbotRan;

import ChatbotRan.components.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Displays chatbot responses to the window.
 */
public class RanUi {
    private VBox container;

    private final StringBuilder sb = new StringBuilder();
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image ranImage = new Image(this.getClass().getResourceAsStream("/images/DaRan.png"));

    /**
     * Prints the startup message.
     */
    public void greet() {
        addOutputLn("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        addOutputLn(art);
        addOutputLn("What would you like to do today?");
        displayBuiltOutput();
    }


    /**
     * Prints all tasks in the TaskList.
     *
     * @param taskList list of tasks
     */
    public void printTasks(TaskList taskList) {
        int size = taskList.size();
        if (size == 0) {
            addOutputLn("You haven't got any tasks.");
        } else {
            for (int i = 0; i < size; i++) {
                addOutputLn("Task " + (i + 1) + ":" + taskList.get(i));
            }
        }
    }

    /**
     * Prints a generic error message.
     */
    public void unknown() {

        addOutputLn("I didn't understand that.");
    }

    /**
     * Prints response after adding a Task
     *
     * @param task task
     */
    public void addTask(Task task) {
        addOutputLn("I've added this task to the list: ");
        addOutputLn(task.toString());
    }

    void printNumber(int size) {
        if (size == 1) {
            addOutputLn("There is now 1 task in the list");
        } else {
            addOutputLn("There are now " + size + " tasks in the list");
        }
    }

    /**
     * Prints response after deleting a task.
     *
     * @param task task
     */
    public void delete(Task task) {
        addOutputLn("I've deleted this task: ");
        addOutputLn(task.toString());
    }

    /**
     * Prints the shutdown message.
     */
    public void bye() {
        addOutputLn("Goodbye, please return soon.");
    }

    /**
     * Prints response after attempting to unmark a task.
     *
     * @param completed whether the task was complete
     */
    public void unmark(boolean completed) {
        if (completed) {
            addOutputLn("If that's the case, I'll set that task as incomplete: ");
        } else {
            addOutputLn("That task is already incomplete: ");

        }
    }

    /**
     * Prints response after attempting to mark a task.
     *
     * @param completed whether the task was complete
     */
    public void mark(boolean completed) {

        if (!completed) {
            addOutputLn("Alright. I have marked this task as complete: ");
        } else {
            addOutputLn("That task is already complete: ");
        }
    }

    /**
     * Prints one task.
     *
     * @param task task
     */
    public void printTask(Task task) {
        addOutputLn(task.toString());
    }

    /**
     * Displays results of an error in a red dialog box.
     *
     * @param e error from parsing a task
     */
    public void displayError(TaskException e) {
        container.getChildren().add(DialogBox.getErrorDialog(e.getMessage(), ranImage));
    }

    /**
     * Prints results of a search for a task.
     *
     * @param tasks result of the search
     */
    public void found(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            addOutputLn("No tasks contain that string.");
        } else {
            addOutputLn("Found " + tasks.size() + " match" + (tasks.size() == 1 ? "" : "es"));
            for (Task t : tasks) {
                printTask(t);
            }
        }
    }

    /**
     * Prints one line to a new dialog.
     *
     * @param line line to add
     */
    public void addOutputLn(String line) {
        this.sb.append(line);
        this.sb.append("\n");
    }

    /**
     * Flushes accumulated output into one new DialogBox.
     */
    public void displayBuiltOutput() {
        if (sb.length() == 0) {
            return;
        }
        String response = sb.toString();
        sb.setLength(0);
        container.getChildren().add(DialogBox.getDukeDialog(response, ranImage));
    }

    public void setContainer(VBox dialogContainer) {
        this.container = dialogContainer;
    }

    /**
     * Displays user input in a dialog box.
     *
     * @param input user input
     */
    public void displayInput(String input) {
        container.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }
}
