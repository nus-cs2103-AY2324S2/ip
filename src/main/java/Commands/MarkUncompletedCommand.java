package Commands;

import java.io.IOException;

public class MarkUncompletedCommand extends Command{
    int index;
    public static final String COMMAND_WORD = "unmark";
    public MarkUncompletedCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() throws IOException {
        this.taskList.getTask(this.index).markUncompleted();
        String outputString = "I've marked this task as uncompleted:\n"
                + this.taskList.getTask(this.index).toString();
        return outputString.toString();
    }
}