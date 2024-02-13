package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DukeList;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to add new Todo.
 */
public class TodoCommand implements Command {

    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Todo based on description extracted from input and adds to TaskList.
     *
     * @param dukeList Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException Not thrown.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        TaskList list = (TaskList) dukeList;
        Task t = new Todo(input.substring(5));
        list.add(t);
        storage.writeToFile(list);
        return ui.showAdded(t, list);
    }

    @Override
    public boolean equals(Object a) {
        TodoCommand tc = (TodoCommand) a;
        return this.input.equals(tc.input);
    }
}
