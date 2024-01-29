package Commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.ToDo;
import Storage.Storage;

import java.io.IOException;
import java.util.List;

public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private final Task todo;
    public AddTodoCommand(String input) {
        this.todo = new ToDo(input);
    }
    @Override
    public String execute() throws IOException {
        this.taskList.addTask(this.todo);
        return "I have added this task:\n" + this.todo + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
