public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        return "T | " + done + " | " + super.toString() + "\n";
    }
}