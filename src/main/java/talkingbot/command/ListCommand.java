package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class ListCommand extends Command {
    public ListCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        System.out.println(taskList);
    }
}
