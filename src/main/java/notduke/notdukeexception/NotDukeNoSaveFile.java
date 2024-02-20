package notduke.notdukeexception;

public class NotDukeNoSaveFile extends NotDukeException {
    @Override
    public String toString() {
        return String.format("%s No save file could be found!", super.toString());
    }
}
