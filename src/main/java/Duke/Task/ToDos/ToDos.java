package Duke.Task.ToDos;

import Duke.Task.Task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }

    @Override
    public String toFile() {
        if(isDone){
            return "T|1|" + description;
        } else {
            return "T|0|" + description;
        }
    }
}
