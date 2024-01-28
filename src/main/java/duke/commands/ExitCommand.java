package duke.commands;

import java.io.IOException;

import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * This class implements the exit command that leads to the exit of the bot when executed.
 * 
 * @author delishad21
 */
public class ExitCommand extends Command {

    /**
     * Basic constructor, sets isExit to true.
     */
    public ExitCommand() {
        super(true);
    }
    
    
    
    /** 
     * Method for executing exit command, saves data into save file using storage object.
     * 
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTodoData(tasks, ui);
        } catch (IOException e) {
            System.out.println("Data not saved: " + e.getMessage());
        }
    }
}
