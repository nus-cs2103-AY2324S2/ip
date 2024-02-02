package duke.dukeexception;

public class DukeCannotBeUnmarked extends DukeException{

    @Override
    public String toString() {
        return String.format("%s duke.task.Task is already unmarked!",super.toString());
    }
}
