package notduke.notdukeexception;

public class NotDukeSaveError extends NotDukeException {
    @Override
    public String toString() {
        return String.format("%s Unable to save!", super.toString());
    }
}
