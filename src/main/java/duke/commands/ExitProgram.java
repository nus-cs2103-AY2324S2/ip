package duke.commands;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskException;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public class ExitProgram extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException {
        System.out.println("     Thanks for using, see you again! RAHHH");
    }


}
