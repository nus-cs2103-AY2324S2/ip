package duke;

public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void execute(String description){}
    
}
