package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;

/**
 * Command to find an event in the task list.
 */
public class FindCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of FindCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws EmptyInputException If user did not input description.
     */
    public FindCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String str;
        String input = ui.getInput();
        if (input.length() < 4) {
            throw new EmptyInputException("find");
        }
        String keyword = input.substring(5);
        str = taskList.find(keyword);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
