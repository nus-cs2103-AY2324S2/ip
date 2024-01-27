package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public abstract class Command {
    private final String[] commandArr;

    /**
     * Constructor for the Command class.
     *
     * @param commandArr String array containing the command.
     */
    public Command(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Returns the String array specifying the command to be executed.
     *
     * @return String array containing the command.
     */
    public String[] getCommandArr() {
        return this.commandArr;
    }

    /**
     * Abstract method used in subclasses to run a certain command.
     *
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    public abstract void runCommand(TaskList taskList, SaveFile saveFile, Ui ui);
}
