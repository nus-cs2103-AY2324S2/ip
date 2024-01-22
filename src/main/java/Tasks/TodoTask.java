package Tasks;

public class TodoTask extends Task{
    public TodoTask(String desc) {
        super(desc);
    }

    public String getStatusIcon() {
        return (this.isDone() ? "[T][X] " : "[T][ ] ");
    }

    public String toString() {
        return this.getStatusIcon() + this.getDesc();
    }
}
