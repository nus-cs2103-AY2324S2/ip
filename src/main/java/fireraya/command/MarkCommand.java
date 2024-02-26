package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;

/**
 * Class to handle a Mark Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class MarkCommand extends Command{
    private int index;


    /**
     * Constructor for the Mark Command.
     *
     * @param index Integer value of index in the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        if (tasks.size() <= index || index < 0) {
            throw new FirerayaException("That task does not exist!");
        }

        tasks.markAsDone(index);
        String a = ui.print("Good job completing these tasks!:\n");
        String b = ui.listTaskAt(tasks.getTasks(), index);
        storage.saveToFile(tasks.getTasks());
        return a + b;
    }

}
