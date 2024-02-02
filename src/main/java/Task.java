import java.time.LocalDate;


public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String saveOutput() {
        return String.format("| %d | %s", isDone ? 1 : 0, name);
    }

    public String happenOn(LocalDate date) {
        return "";
    }

    public String taskInfo() {
        String output = "";
        if (isDone) {
            output += "[X]";
        } else {
            output += "[ ]";
        }
        return output + " " + name;
    }

    public String mark() throws DukeCannotBeMarked {
        if (isDone) {
            throw new DukeCannotBeMarked();
        }
        isDone = true;
        return "Nice! I've marked this task as done:" + this.taskInfo();
    }

    public String unmark() throws DukeCannotBeUnmarked {
        if (!isDone) {
            throw new DukeCannotBeUnmarked();
        }
        isDone = false;
        return "OK, I've marked this task as not done yet:" + this.taskInfo();
    }
}