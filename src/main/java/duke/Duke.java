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

    public Duke() {
    }

    /**
     * Creates a Duke chatbot object.
     *
     * @param filePath the path of the file to save the tasks to
     */
    public Duke(String filePath, Ui ui) {
        this.ui = ui;
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the response of the chatbot to the user input.
     *
     * @param input the user input
     * @return the response of the chatbot
     */
    String getResponse(String input) {
        try {
            assert input != null : "Input should not be null";
            Parser parser = new Parser(input);

            String commandWord = parser.getCommandWord();
            boolean isAddedToTasks = false;
            switch (commandWord) {
            case "bye":
                storage.writeToFile(tasks);
                return ui.showBye();
            case "list":
                return ui.showTaskList(tasks);
            case "mark":
                Task markTask = tasks.markDone(parser.getIndex());
                assert markTask != null : "Task should not be null";
                return ui.showDone(markTask);
            case "unmark":
                Task unmarkTask = tasks.unmarkDone(parser.getIndex());
                assert unmarkTask != null : "Task should not be null";
                return ui.showUnmark(unmarkTask);
            case "delete":
                Task deleteTask = tasks.deleteTask(parser.getIndex());
                assert deleteTask != null : "Task should not be null";
                return ui.showDelete(deleteTask, tasks.getSize());
            case "find":
                TaskList filteredTasks = tasks.findTasks(parser.getDescription());
                assert filteredTasks != null : "Filtered tasks should not be null";
                return ui.showTaskList(filteredTasks);
            case "todo":
                Task task = new Todo(parser.getDescription());
                isAddedToTasks = tasks.addTask(task);
                assert isAddedToTasks : "Todo could not be added to tasks";
                return ui.showAddTask(task, tasks.getSize());
            case "deadline":
                Task deadlineTask = new Deadline(parser.getDescription(), parser.getBy());
                isAddedToTasks = tasks.addTask(deadlineTask);
                assert isAddedToTasks : "Deadline could not be added to tasks";
                return ui.showAddTask(deadlineTask, tasks.getSize());
            case "event":
                Task eventTask = new Event(parser.getDescription(), parser.getFromTo()[0], parser.getFromTo()[1]);
                isAddedToTasks = tasks.addTask(eventTask);
                assert isAddedToTasks : "Event could not be added to tasks";
                return ui.showAddTask(eventTask, tasks.getSize());
            default:
                return ui.commandNotUnderstood();
            }

        } catch (IndexOutOfBoundsException e) {
            return ui.showError("The index given for the task does not exist. \n\t"
                + "Please use the following format: mark <index>");
        } catch (NumberFormatException e) {
            return ui.showError("Please enter a valid number to modify task");
        } catch (DateTimeParseException e) {
            return ui.showError("Please enter a valid date in the format dd-mm-yyyy HHmm");
        } catch (IOException e) {
            return ui.showError("Failed to save tasks to file");
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }


}
