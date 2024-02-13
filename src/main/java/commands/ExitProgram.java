package commands;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import mainUtils.Storage;
import mainUtils.Ui;
import tasks.TaskList;

public class ExitProgram extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskException, InvalidDateException {
        System.out.println("     Thanks for using, see you again! RAHHH");
    }


}
