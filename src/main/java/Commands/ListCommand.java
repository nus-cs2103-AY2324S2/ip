package Commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.Event;
import Storage.Storage;

import java.io.IOException;
import java.util.List;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute() throws IOException {
        StringBuilder output = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            output.append((i+1) + ". " + taskList.getTask(i).toString() + "\n");
        }
        return output.toString();
    }
}
