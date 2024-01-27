package duke.commands;
import java.io.IOException;

import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTodoData(tasks, ui);
        } catch (IOException e) {
            System.out.println("Data not saved: " + e.getMessage());
        }
    }
}
