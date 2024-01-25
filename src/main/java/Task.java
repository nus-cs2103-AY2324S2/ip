import java.util.Scanner;
public class Task {
    private String taskName;
    private boolean done;
    private String start;
    private String end;

    private String deadline;

    private char taskType;


    // we need to use different constructors for the different classes
    // for todo: only taskName
    // for deadline: taskName + deadline
    // for event: taskName + start + end

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
        this.taskType = 'T';
    }

    public Task(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.done = false;
        this.taskType = 'D';
    }

    public Task(String taskName, String start, String end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
        this.done = false;
        this.taskType = 'E';
    }

    public String getStatusIcon() {
        return (this.done ? "X" : " "); // mark done task with X
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public void setDone(boolean isDone) {
        this.done = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "] [" + this.getStatusIcon() + "] " + this.taskName;
    }
}




