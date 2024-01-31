package duke.exception;

public class TasksFileException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "An error occurred with the tasks file.\n";
    }
}
