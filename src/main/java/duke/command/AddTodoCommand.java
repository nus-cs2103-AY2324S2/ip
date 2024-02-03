package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    private String description;
    public AddTodoCommand(Parser.Cmd type, String description) {
        super(type);
        this.description = description;
    }
    @Override
    public void run(TaskList taskList) {
        Todo todo = new Todo(this.description);
        String[] data = {this.description};
        taskList.addTask(todo, "todo", data);
    }
}
