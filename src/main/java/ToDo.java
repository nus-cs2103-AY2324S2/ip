public class ToDo extends Task {

    ToDo(String desc) {
        super(desc);
    }

    ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    public String toStore() {
        // need to store status as well
        return "T | " + super.toStore();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
