package duke.command;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class TodoCommand extends Command {

    private String input;
    public TodoCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Storage storage) {
        try {
            String description;
            description = this.input.trim().substring("todo".length()).trim();
            if (description.isEmpty()) {
                return "Add description for TODO";
            } else {
                storage.addToFile(input);
                return tasks.addTodo(description);
            }
        } catch (IOException e) {
            return "Invalid todo task format";
        }
    }
}
