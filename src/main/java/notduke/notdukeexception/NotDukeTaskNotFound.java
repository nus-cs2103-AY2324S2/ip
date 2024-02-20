package notduke.notdukeexception;

public class NotDukeTaskNotFound extends NotDukeException {

    private int index;
    public NotDukeTaskNotFound(int index) {
        this.index = index;
    }
    @Override
    public String toString() {
        return String.format("%s Task No. %d cannot be found!", super.toString(), index);
    }
}
