package notduke.notdukeexception;

public class NotDukeInValidTimeframe extends NotDukeException {

    @Override
    public String toString() {
        return String.format("%s \"To\" Date and Time is after \"From\"", super.toString());
    }
}
