package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a duke chatbot that handles users input and add tasks.
 * It also saves the tasks to a file.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke chatbot object.
     *
     * @param filePath the path of the file to save the tasks to
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTaskslist());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Instantiates a Duke chatbot and runs it.
     *
     * @param args the arguments passed in
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser(ui.readCommand());

                String commandWord = parser.getCommandWord();
                switch (commandWord) {
                case "bye":
                    isExit = true;
                    ui.showBye();
                    break;
                case "list":
                    ui.showTaskList(tasks);
                    break;
                case "mark":
                    Task markTask = tasks.markDone(parser.getIndex());
                    ui.showDone(markTask);
                    break;
                case "unmark":
                    Task unmarkTask = tasks.unmarkDone(parser.getIndex());
                    ui.showUnmark(unmarkTask);
                    break;
                case "delete":
                    Task deleteTask = tasks.deleteTask(parser.getIndex());
                    ui.showDelete(deleteTask, tasks.getSize());
                    break;
                case "find":
                    TaskList filteredTasks = tasks.findTasks(parser.getDescription());
                    ui.showTaskList(filteredTasks);
                    break;
                case "todo":
                    Task task = new Todo(parser.getDescription());
                    tasks.addTask(task);
                    ui.showAddTask(task, tasks.getSize());
                    break;
                case "deadline":
                    Task deadlineTask = new Deadline(parser.getDescription(), parser.getBy());
                    tasks.addTask(deadlineTask);
                    ui.showAddTask(deadlineTask, tasks.getSize());
                    break;
                case "event":
                    Task eventTask = new Event(parser.getDescription(), parser.getFromTo()[0], parser.getFromTo()[1]);
                    tasks.addTask(eventTask);
                    ui.showAddTask(eventTask, tasks.getSize());
                    break;
                default:
                    ui.commandNotUnderstood();
                }

            } catch (IndexOutOfBoundsException e) {
                ui.showError("The index of a task cannot be empty. \n\t"
                    + "Please use the following format: mark <index>");
            } catch (NumberFormatException e) {
                ui.showError("Please enter a valid number to modify task");
            } catch (DateTimeParseException e) {
                ui.showError("Please enter a valid date in the format dd-mm-yyyy");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.showError("Failed to save tasks to file");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

}
