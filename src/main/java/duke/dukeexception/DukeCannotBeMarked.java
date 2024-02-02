package duke.dukeexception;

public class DukeCannotBeMarked extends DukeException{

    @Override
    public String toString() {
        return String.format("%s duke.task.Task is already marked!",super.toString());
    }
}
