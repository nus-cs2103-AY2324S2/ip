package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class ListCommand extends Command{
    public ListCommand(){
    }
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        String taskoutput = taskList.printOutput();
        sb.append("\n").append(taskoutput);
        ui.setCommandOutput(sb.toString());
    }
}
