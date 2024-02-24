import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class list_Entry_Deadline extends list_Entry {
        public list_Entry_Deadline(String task, boolean check, LocalDate tEnd) {
            super(task, check, TYPE_DEADLINE, tEnd);
        }

    @Override
    public String toString() {
            return super.toString() + " (by: "+ this.taskEnd.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
