package main.java.emis.command;
import main.java.emis.*;
import main.java.emis.task.Event;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String d, String f, String t) {
        this.description = d;
        this.from = f;
        this.to = t;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.addTask(new Event(this.description, this.from, this.to));
        s.updateStorage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}