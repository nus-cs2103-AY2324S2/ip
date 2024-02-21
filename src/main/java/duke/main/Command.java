package duke.main;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Task;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.IOException;

/**
 * Represents the command processor of the Duke application, responsible for
 * executing user commands and interfacing between the UI, parser, storage, and task list.
 */
public class Command {
    
    /**
     * Retrieves a string representation of the list of tasks.
     *
     * @param ui  The UI component of the application.
     * @param list The task list.
     * @return A string representing the listed tasks.
     */
    public String getListResponse(Ui ui, TaskList list) {
        return ui.listing(list);
    }
    
    /**
     * Processes the marking of a task as done, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input.
     * @return A response message after marking a task as done.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getMarkResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleMarkError(input, list);
        Task taskToMark = parser.getTaskTobeMarked(input, list);
        taskToMark.markDone();
        storage.changeFileContent(list);
        return ui.marking(taskToMark);
    }
    
    /**
     * Processes the unmarking of a task as not done, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input.
     * @return A response message after unmarking a task as not done.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getUnmarkResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleUnmarkError(input, list);
        Task taskToUnmark = parser.getTaskToBeUnmarked(input, list);
        taskToUnmark.markUndone();
        storage.changeFileContent(list);
        return ui.unmarking(taskToUnmark);
    }
    
    /**
     * Processes the addition of a new ToDo task, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input.
     * @return A response message after adding a new ToDo task.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getTodoResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleTodoError(input);
        Task todoTask = parser.createToDo(input);
        storage.addTaskToFile(todoTask);
        return ui.echo(todoTask, list);
    }
    
    /**
     * Processes the addition of a new Deadline task, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input.
     * @return A response message after adding a new Deadline task.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getDeadlineResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleDeadlineError(input);
        Task deadlineTask = parser.createDeadline(input);
        storage.addTaskToFile(deadlineTask);
        return ui.echo(deadlineTask, list);
    }
    
    /**
     * Processes the addition of a new Event task, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input.
     * @return A response message after adding a new Event task.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getEventResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleEventError(input);
        Task eventTask = parser.createEvent(input);
        storage.addTaskToFile(eventTask);
        return ui.echo(eventTask, list);
    }
    
    /**
     * Processes the deletion of a task, updates storage, and returns a response message.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param storage  The storage handler to persist task changes.
     * @param list     The task list.
     * @param input    The user input, expected to contain the index of the task to be deleted.
     * @return A response message after deleting a task.
     * @throws DukeException If an error occurs during processing.
     * @throws IOException If an I/O error occurs.
     */
    public String getDeleteResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        int index = Integer.parseInt(input.substring(7));
        ui.handleDeleteError(list, index);
        Task taskDelete = parser.getTaskToDelete(input, list);
        String result = ui.deleting(taskDelete, list);
        list.delete(taskDelete);
        storage.changeFileContent(list);
        return result;
    }
    
    /**
     * Processes a search for tasks containing a specific keyword and returns a response message with matching tasks.
     *
     * @param ui       The UI component of the application.
     * @param parser   The parser to interpret user input.
     * @param list     The task list.
     * @param input    The user input, containing the keyword to search for.
     * @return A response message listing tasks that contain the specified keyword.
     */
    public String getFindResponse(Ui ui, Parser parser, TaskList list, String input) {
        String keyword = parser.getKeywordForFind(input);
        return ui.finding(list, keyword);
    }
    
    /**
     * Handles the response for help command.
     * @param ui The UI component of the application.
     * @return A response for help command.
     */
    public String getHelpResponse(Ui ui) {
        return ui.help();
    }
    
    /**
     * Closes the application when bye command is triggered, with a pause of 2 seconds
     * to allow the user to read the response.
     */
    public void byeResponse() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            Platform.exit();
            System.exit(0);
        });
        delay.play();
    }
}
