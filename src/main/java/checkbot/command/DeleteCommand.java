package checkbot.command;

import checkbot.*;
import checkbot.exception.InvalidIndexException;
import checkbot.task.Task;
import checkbot.task.TodoList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException {
        Task deletedTask = todoList.deleteTask(index);
        ui.print("Alright, I deleted this task:\n"
                + Ui.INDENTATION + deletedTask + "\n"
                + "You have now " + todoList.getLength() + " task"
                + (todoList.getLength() > 1 ? "s" : "")
                + " in the list.");
    }
}
