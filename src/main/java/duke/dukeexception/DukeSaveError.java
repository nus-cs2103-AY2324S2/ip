package duke.dukeexception;

public class DukeSaveError extends DukeException{
    @Override
    public String toString() {
        return String.format("%s Unable to save!", super.toString());
    }
}
