package main.java.emis.command;
import main.java.emis.*;
import main.java.emis.task.Deadline;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String d, String b) {
        this.description = d;
        this.by = b;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.addTask(new Deadline(this.description, this.by));
        s.updateStorage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}