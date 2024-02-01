package duke.command;

import duke.Ui;
import duke.TaskList;

public class CommandBye extends Command {

    public CommandBye(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(String description) {
        ui.print("Goodbye. See you later!");
    }
    
}
