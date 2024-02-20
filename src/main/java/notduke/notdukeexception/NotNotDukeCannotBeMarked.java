package notduke.notdukeexception;

public class NotNotDukeCannotBeMarked extends NotDukeException {

    @Override
    public String toString() {
        return String.format("%s Task is already marked!", super.toString());
    }
}
