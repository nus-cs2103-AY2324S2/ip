package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** The TodoCommand class represents the "todo" command.*/
public class TodoCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /** Full command. */
    private String command;

    /**
     * Constructs a TodoCommand object.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     * @param command Full command input.
     */
    public TodoCommand(TaskList taskList, Ui ui, Storage storage, String command) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
        this.command = command;
    }

    @Override
    public String handle() throws AuroraException {
        String description = parseDescription();
        String message = addTodoAndReturnMessage(description);
        saveTasks();
        assert !message.equals("Command not executed.") : "Todo command not executed.";
        return message;
    }

    /**
     * Returns the description of the Todo object to be created.
     *
     * @return Description of the Todo object to be created.
     * @throws AuroraException If the input format of the Todo command was incorrect.
     */
    private String parseDescription() throws AuroraException {
        String[] descriptionSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_TODO_FORMAT);
        }
        return descriptionSplit[1];
    }

    /**
     * Returns a String alerting the user that the new Todo object has been created and added to the task list.
     * Adds the new Todo object to the task list.
     *
     * @param description Description of the Todo object created.
     * @return String alerting the user that the new Todo object has been created and added to the task list.
     */
    private String addTodoAndReturnMessage(String description) {
        this.taskList.addTodo(description);
        return this.ui.getEchoAddTaskString(this.taskList);
    }

    /**
     * Saves the task list containing the new todo object created to the storage file.
     *
     * @throws AuroraException If an error occurs while saving the task list to the storage file.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save todo to file: " + exception.getMessage());
        }
    }

}
