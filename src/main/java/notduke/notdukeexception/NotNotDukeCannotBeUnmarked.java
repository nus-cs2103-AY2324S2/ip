package notduke.notdukeexception;

public class NotNotDukeCannotBeUnmarked extends NotDukeException {

    @Override
    public String toString() {
        return String.format("%s Task is already unmarked!", super.toString());
    }
}
