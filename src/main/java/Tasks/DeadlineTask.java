package Tasks;

public class DeadlineTask extends Task {
    private String end;
    public DeadlineTask(String desc, String end) {
        super(desc);;
        this.end = end;
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[D][X] " : "[D][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc() + " (by: " + this.end + ")";
    }
}
