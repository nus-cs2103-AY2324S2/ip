package main.java.emis.command;
import main.java.emis.*;
import main.java.emis.task.ToDo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.addTask(new ToDo(this.description));
        s.updateStorage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}