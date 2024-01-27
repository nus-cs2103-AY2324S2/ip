package duke.tasks;

public class TodoTask extends Task{
    public TodoTask(String desc) {
        super(desc);
    }

    public TodoTask(String desc, String isDone) {
        super(desc, isDone);
    }
    public String getStatusIcon() {
        return (this.isDone() ? "[T][X] " : "[T][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc();
    }
    public String save() {
        String isDone = this.isDone() ? "1" : "0";
        return "T," + isDone + "," + this.getDesc();
    }
}
