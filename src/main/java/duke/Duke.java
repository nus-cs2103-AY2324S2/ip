package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;

/**
 * Duke is a task management system that allows users to manage their tasks through a command-line interface.
 */
public class Duke {
    /**
     * Entry point of the Duke application.
     *
     * @param args Command-line arguments.
     * @throws Exception If an error occurs during execution.
     */
    public static void main(String[] args) throws Exception {
        Ui.printWelcomeMessage();

        ArrayList<Task> tasks = new ArrayList<>();
        Storage.loadTasksFromFile(tasks);

        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String input = bufferedReader.readLine();
            String[] token = input.split(" ");

            TaskList taskList = new TaskList();

            if (input.equals("bye")) {
                break;
            } else if (token.length == 1 && !input.equals("list")) {
                Ui.printErrorMessage("SOMETHING WENT WRONG!! Invalid input.");
            } else if (input.equals("list")) {
                Ui.printTaskList(tasks);
            } else if (token[0].equals("mark") || token[0].equals("unmark")) {
                taskList.markTaskAsDoneOrUndone(token, tasks);
            } else if (token[0].equals("deadline")) {
                taskList.addDeadlineTask(input, tasks);
            } else if (token[0].equals("event")) {
                taskList.addEventTask(input, tasks);
            } else if (token[0].equals("todo")) {
                taskList.addTodoTask(input, tasks);
            } else if (token[0].equals("delete")) {
                taskList.removeTask(token, tasks);
            } else {
                Task n = new Task(input);
                tasks.add(n);
                Ui.printAddedMessage(input);
            }

            Storage.saveTasksToFile(tasks);
        }

        Ui.printGoodbyeMessage();
    }
}