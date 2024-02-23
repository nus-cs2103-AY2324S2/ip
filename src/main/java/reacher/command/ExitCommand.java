package reacher.command;

import javafx.application.Platform;
import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;
import reacher.ui.MainWindow;

/**
 * Command that when executed ends the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes command by printing message.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Local file storage.
     * @return
     */
    @Override
    public String execute(String input, TaskList tasks, Ui ui, Storage storage){
        return ("Bye!");
    }

    @Override
    public boolean equals(Object object){
        return object instanceof ExitCommand;
    }
}
