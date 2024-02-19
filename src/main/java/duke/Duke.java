package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Duke is a task management system that allows users to manage their tasks through a command-line interface
 */
public class Duke extends Application {
    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskList taskList = new TaskList();

    /**
     * Launches the JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overrides the start method from Application class.
     * Loads tasks from file when the JavaFX application starts.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        Storage.loadTasksFromFile(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Processes user input and generates a response accordingly.
     *
     * @param input The user input string.
     * @return The response generated based on the input.
     */
    String getResponse(String input) {
        assert input != null : "Input must not be null";
        // Process user input and generate response
        String[] tokens = input.split(" ");
        StringBuilder response = new StringBuilder();
        assert response != null : "Response StringBuilder must not be null";

        if (input.equals("bye")) {
            response.append("    Bye. Hope to see you again soon!");
            Platform.exit(); // Exit the application
        } else if (input.equals("list")) {
            response.append(TaskList.getTaskList(tasks));
        } else if (tokens[0].equals("mark") || tokens[0].equals("unmark")) {
            response.append(taskList.markTaskAsDoneOrUndone(tokens, tasks));
        } else if (tokens[0].equals("deadline")) {
            response.append(taskList.addDeadlineTask(input, tasks));
        } else if (tokens[0].equals("event")) {
            response.append(taskList.addEventTask(input, tasks));
        } else if (tokens[0].equals("todo")) {
            response.append(taskList.addTodoTask(input, tasks));
        } else if (tokens[0].equals("delete")) {
            response.append(taskList.removeTask(tokens, tasks));
        } else if (tokens[0].equals("find")) {
            response.append(taskList.findTask(input, tasks));
        } else {
            response.append("     SOMETHING WENT WRONG!! Invalid input.");
        }

        // Save tasks to duke.txt file
        Storage.saveTasksToFile(tasks);

        return response.toString();
    }
}