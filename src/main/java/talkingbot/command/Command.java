package talkingbot.command;

import talkingbot.util.TaskList;
import talkingbot.util.SaveFile;
import talkingbot.util.Ui;

public abstract class Command {
    private final String[] commandArr;

    public Command(String[] commandArr) {
        this.commandArr = commandArr;
    }

    public String[] getCommandArr() {
        return this.commandArr;
    }

    public abstract void runCommand(TaskList taskList, SaveFile saveFile, Ui ui);
}
