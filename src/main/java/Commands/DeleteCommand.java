package Commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.ToDo;
import Storage.Storage;

import java.io.IOException;
import java.util.List;

public class DeleteCommand extends Command{
    public static final String COMMAND_WORD = "delete";
    int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute() throws IOException {
        Task toBeDeleted = this.taskList.getTask(this.index);
        this.taskList.delete(this.index);
        String outputString = "I have removed the following task:\n" + toBeDeleted.toString()+"\nNow you have "+ this.taskList.size() + " tasks in your list.";
        return outputString;
    }
}
