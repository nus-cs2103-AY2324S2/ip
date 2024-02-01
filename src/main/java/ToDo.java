public class ToDo extends Task {
    ToDo(String name, boolean done) {
        super(name, done);
    }

    ToDo(String name) {
        this(name, false);
    }

    public String typeOfTask() {
        return "T";
    }
}
