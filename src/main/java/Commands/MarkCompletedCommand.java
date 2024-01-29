package Commands;

import java.io.IOException;

public class MarkCompletedCommand extends Command{
    int index;
    public static final String COMMAND_WORD = "mark";
    public MarkCompletedCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() throws IOException {
        this.taskList.getTask(this.index).markCompleted();
        String outputString = "I've marked this task as completed:\n"
                + this.taskList.getTask(this.index).toString();
        return outputString.toString();
    }
}