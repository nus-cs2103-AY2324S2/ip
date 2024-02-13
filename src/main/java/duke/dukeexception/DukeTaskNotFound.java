package duke.dukeexception;

public class DukeTaskNotFound extends DukeException {

    private int index;
    public DukeTaskNotFound(int index) {
        this.index = index;
    }
    @Override
    public String toString() {
        return String.format("%s Task No. %d cannot be found!", super.toString(), index);
    }
}
