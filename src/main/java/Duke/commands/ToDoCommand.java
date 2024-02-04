package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;
import Duke.exceptions.EmptyDescriptionException;
import Duke.tasks.Task;
import Duke.tasks.ToDo;
public class ToDoCommand extends Commands {
    private String[] words;
    public ToDoCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        Task newTask = new ToDo(words[1]);
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
