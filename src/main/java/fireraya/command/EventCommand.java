package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Event;

public class EventCommand extends Command{

    private String description;
    private String from;
    private String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Event e = new Event(description, from, to);
        tasks.add(e);
        String a = ui.taskAddedMsg(e, tasks.size());
        storage.saveToFile(tasks.getTasks());
        return a;
    }

}
