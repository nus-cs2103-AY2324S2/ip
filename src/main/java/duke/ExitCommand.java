package duke;

/**
 * Represents the command to exit the chatbot program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    /**
     * Executes the saving of tasks to the storage and sets isExit to true.
     *
     * @param taskList Tasklist with tasks to be saved.
     * @param ui User interface.
     * @param storage Storage to store Tasklist content.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        setExit(true);
        try {
            storage.save(taskList);
        } catch (DukeException e) {
            ui.showMessage("Unable to save :(");
        }
        return "";
    }
}
