import java.time.LocalDate;

public class list_Entry_Event extends list_Entry {
    public list_Entry_Event(String task, boolean check, LocalDate tStart, LocalDate tEnd) {
        super(task, check, TYPE_EVENT, tStart, tEnd);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: "+ this.taskStart + " to: " + this.taskEnd + ")";
    }
}
