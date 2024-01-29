package Commands;

import Exceptions.InvalidInputException;
import TaskList.Tasks.Task;
import TaskList.Tasks.Deadline;
import Storage.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class AddDeadlineCommand extends Command{
    public static final String COMMAND_WORD = "deadline";
    private final Task deadline;

    public AddDeadlineCommand(String deadlineName, LocalDateTime by) throws InvalidInputException {
        this.deadline = new Deadline(deadlineName, by);
    }
    @Override
    public String execute() throws IOException {
        this.taskList.addTask(this.deadline);
        return "I have added this task:\n" + this.deadline + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
