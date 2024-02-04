package panda.command;
import panda.component.*;
import panda.task.Task;
public class NewTaskCommand extends Command {
    private Task task;

    public NewTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tlist) {
        tlist.insert(task);
    }

    public void execute(TaskList tlist, Ui ui, Storage cacheFile) {
        tlist.insert(task);
        cacheFile.save(tlist);
        ui.showReply("Got it. I've added this task:\n " + tlist.taskString(tlist.size() - 1) + "\nNow you have " + tlist.size() + " tasks in the list.");
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof NewTaskCommand)) {
            return false;
        }
         
        NewTaskCommand c = (NewTaskCommand) o;

        return task.equals(c.task);
    }
}
