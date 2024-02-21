package aurora.command;

import java.io.IOException;

import aurora.objects.AuroraException;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The TodoCommand class handles the "todo" command.
 */
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
     * Constructor for the TodoCommand class.
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
     * Helper function to parse the description.
     *
     * @return Description of the Todo.
     * @throws AuroraException if the input format of the command was incorrect.
     */
    private String parseDescription() throws AuroraException {
        String[] descriptionSplit = Parser.splitAtFirstBlank(this.command);
        if (descriptionSplit.length < 2) {
            throw new AuroraException(AuroraException.INVALID_TODO_FORMAT);
        }
        return descriptionSplit[1];
    }

    /**
     * Helper method to add the ToDo to the taskList and obtaining the alert message.
     *
     * @param description Description of the Todo,
     * @return The Alert message that the Todo has been added.
     */
    private String addTodoAndReturnMessage(String description) {
        this.taskList.addTodo(description);
        return this.ui.getEchoAddTaskString(this.taskList);
    }

    /**
     * Helper method to save the taskList to the storage file.
     *
     * @throws AuroraException If the taskList could not be saved successfully.
     */
    private void saveTasks() throws AuroraException {
        try {
            this.storage.saveTasks(this.taskList.getTaskList());
        } catch (IOException exception) {
            throw new AuroraException("I'm unable to save todo to file: " + exception.getMessage());
        }
    }

}
