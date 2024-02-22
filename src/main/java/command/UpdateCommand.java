package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import exception.EmptyInputException;

/**
 * Command to exit the program.
 */
public class UpdateCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of byeCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws DukeException If input is not valid.
     */
    public UpdateCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length <= 1) {
            throw new EmptyInputException("update position");
        } else if (input.split(" ").length <= 2) {
            throw new EmptyInputException("update description");
        }
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) - 1;

        String[] inputArr = input.split(" ");
        String newDescription = "";
        for (int i = 2; i < inputArr.length; i++) {
            newDescription = newDescription + " " + inputArr[i];
        }
        newDescription = newDescription.trim();
        String str = taskList.update(newDescription, position);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
