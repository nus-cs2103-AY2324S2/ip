package Duke.commands;

import Duke.util.TaskList;
import Duke.util.UI;
import Duke.util.Storage;
import Duke.exceptions.DukeException;
import Duke.exceptions.EmptyDescriptionException;
import Duke.exceptions.InvalidEventException;
import Duke.tasks.Task;
import Duke.tasks.Event;
public class EventCommand extends Commands {
    private String[] words;
    public EventCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("event");
        }
        int startIdx = words[1].indexOf("/from");
        int endIdx = words[1].indexOf("/to");
        if (startIdx == -1 || endIdx == -1) {
            throw new InvalidEventException();
        }
        String[] dates = words[1].substring(startIdx).split("/from | /to ");
        if (dates.length != 3) {
            throw new InvalidEventException();
        }
        Task newTask = new Event(words[1].substring(0, startIdx),
                dates[1],
                dates[2]);
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
