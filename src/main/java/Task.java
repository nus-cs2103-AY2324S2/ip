import java.util.Scanner;
public class Task {
    protected String taskName;
    protected boolean isDone;
    private String start;
    private String end;

    private String deadline;

    protected char taskType;


    // we need to use different constructors for the different classes
    // for todo: only taskName
    // for deadline: taskName + deadline
    // for event: taskName + start + end

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskType = 'T';
    }

    public Task(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.isDone = false;
        this.taskType = 'D';
    }

    public Task(String taskName, String start, String end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
        this.isDone = false;
        this.taskType = 'E';
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
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
        this.isDone = isDone;
    }

    public boolean getDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "] [" + this.getStatusIcon() + "] " + this.taskName;
    }
}




