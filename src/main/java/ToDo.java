public class ToDo extends Task {

    ToDo(String desc) {
        super(desc);
    }

    ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    public String toSave() {
        // need to store status as well
        return "T - " + super.toSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
