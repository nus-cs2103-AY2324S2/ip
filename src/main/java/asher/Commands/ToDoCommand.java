package asher.Commands;

import asher.BotException;
import asher.Ui.Ui;
import asher.Tasks.TaskList;
import asher.Tasks.Todo;

public class ToDoCommand extends Command {

    public ToDoCommand(String input) {
        super(input);
    }

    public Todo createToDoCommand() throws BotException {
        String[] parts = input.split(" ", 2);
        assert parts.length >= 2 : "Input should contain at least two parts!";

        String description = parts[1].trim();
        assert description != null : "Description should not be null!";

        if (description.isEmpty()) {
            throw new BotException("Todo description cannot be empty!");
        }
        return new Todo(description);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Todo todo = createToDoCommand();
            taskList.addTask(todo);
            String addTaskMessage = ui.showAddTaskMessage(todo);
            int totalTask = taskList.getSize();
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(totalTask);
            return addTaskMessage + "\n" + numberOfTaskMessage;
        } catch (BotException e) {
            return ui.showErrorMessage("Error:" + e.getMessage());
        }

    }
}
