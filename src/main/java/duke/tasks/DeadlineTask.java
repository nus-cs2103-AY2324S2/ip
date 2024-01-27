package duke.tasks;

public class DeadlineTask extends Task {
    private String end;
    public DeadlineTask(String desc, String end) {
        super(desc);;
        this.end = end;
    }

    public DeadlineTask(String desc, String isDone, String end) {
        super(desc, isDone);
        this.end = end;
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[D][X] " : "[D][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (by: " + this.end + ")";
    }
    public String save() {
        String isDone = this.isDone() ? "1" : "0";
        return "D," + isDone + "," + this.getDesc() + "," + this.end;
    }
}
