package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;


/**
 * Represents a command to create a Todo task.
 */
public class CreateTodoCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private String input;

    /**
     * Constructor for the CreateTodoCommand class.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The taskList object to record the tasks.
     * @param storage The Storage object to save and load information.
     * @param input The user input.
     */
    public CreateTodoCommand(Ui ui, TaskList taskList, Storage storage, String input) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
        this.input = input;
    }

    @Override
    public String execute() throws DukeException {
        Task addedTask = null;
        String[] inputArr = input.split(" ");
        String commandWord = inputArr[0];
        addedTask = new Todo(getTaskName(commandWord, this.input));
        taskList.addTask(addedTask);
        return this.ui.printAddMessage(taskList.size(), addedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
