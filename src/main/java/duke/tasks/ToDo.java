package duke.tasks;

public class ToDo extends Task {
    public ToDo(String name, boolean done) {
        super(name, done);
    }

    public ToDo(String name) {
        this(name, false);
    }

    public String typeOfTask() {
        return "T";
    }
}
