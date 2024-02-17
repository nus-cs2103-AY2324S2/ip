package hirwan;

public class Undocommand extends Command {
    Tasklist tasks;
    Tasklist previousTasks;
    public Undocommand(Tasklist tasks, Tasklist previousTasks) {
        this.tasks = tasks;
        this.previousTasks = previousTasks;
    }

    public String getMessage() {
        return "Got it! I have yeeted the previous task.";
    }

    public void undo() {
        this.tasks.deleteList();
        this.tasks = this.previousTasks.copyList();
        Storage.writeTask(this.tasks.getList());
    }
}
