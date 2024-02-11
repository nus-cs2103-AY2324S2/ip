package commands;

import TaskList.Tasks.Task;
import TaskList.Tasks.ToDo;

import java.io.IOException;

/**
 * Represents a command to add a todo to the task list.
 * A <code>AddTodoCommand</code> object corresponds to a command with a description
 * e.g., <code>"todo read book"</code>
 */
public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private final Task todo;

    /**
     * Constructs an AddTodoCommand object with a description.
     * @param input the description of the todo
     */
    public AddTodoCommand(String input) {
        this.todo = new ToDo(input);
    }

    /**
     * Executes the command to add a todo to the task list.
     * @return a string representing the result of executing the command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute() throws IOException {
        assert this.todo != null : "Todo object should not be null.";
        this.taskList.addTask(this.todo);
        return "I have added this task:\n" + this.todo + "\n" +
                "Now you have " + this.taskList.size() + " tasks in your list.";

    }
}
