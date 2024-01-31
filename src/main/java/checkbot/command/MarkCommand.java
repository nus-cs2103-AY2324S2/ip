package checkbot.command;

import checkbot.exception.InvalidIndexException;
import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException {
        todoList.markTask(index);
        ui.print("Good job! I have marked this task as completed:\n"
                + todoList.getTask(index));
    }
}
