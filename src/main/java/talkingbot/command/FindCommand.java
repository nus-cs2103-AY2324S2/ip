package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class FindCommand extends Command {
    public FindCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] commandArr = super.getCommandArr();
        System.out.println(taskList.filterList(commandArr[1]));
    }
}
