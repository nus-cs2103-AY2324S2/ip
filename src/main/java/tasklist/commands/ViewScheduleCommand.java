package tasklist.commands;

import tasklist.Storage;
import tasklist.TaskList;
import tasklist.Ui;

public class ViewScheduleCommand implements Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return " Click on the button!" + 
        "\nred - unmarked deadlines" +
        "\ngreen - marked deadlines" +
        "\nblue - events";
    }
    
}
