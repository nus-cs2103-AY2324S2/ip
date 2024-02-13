package duke.dukeexception;

public class DukeInValidTimeframe extends DukeException {

    @Override
    public String toString() {
        return String.format("%s \"To\" Date and Time is after \"From\"", super.toString());
    }
}
