package duke.action;

import duke.task.Task;


public class Delete implements Action {
    private Task deletedTask;

    public Delete(Task deletedTask) {
        this.deletedTask = deletedTask;
    }

    @Override
    public String response() {
        return "Noted. I've removed this task:\n" + deletedTask.toString() + "\nNow you have "
            + " tasks in the list.";
    }
}

