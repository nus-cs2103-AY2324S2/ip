package Luna;
import java.time.LocalDate;

public class ListEntry {
    public static final String TYPE_TODO = "T";
    public static final String TYPE_DEADLINE = "D";
    public static final String TYPE_EVENT = "E";

    public enum taskType {
        T,
        D,
        E,

    }

    protected String task;
    protected boolean check;
    protected String type;

    protected LocalDate taskStart;

    protected LocalDate taskEnd;


    public ListEntry(String task, boolean check, String type, LocalDate tStart, LocalDate tEnd) {
        this.task = task;
        this.check = check;
        this.type = type;
        this.taskStart = tStart;
        this.taskEnd = tEnd;
    }

    public ListEntry(String task, boolean check, String type, LocalDate tEnd) {
        this.task = task;
        this.check = check;
        this.type = type;
        this.taskStart = null;
        this.taskEnd = tEnd;
    }

    public ListEntry(String task, boolean check, String type) {
        this.task = task;
        this.check = check;
        this.type = type;
        this.taskStart = null;
        this.taskEnd = null;
    }




    public void markEntry () {
        this.check = true;
    }

    public void unmarkEntry() {
        this.check = false;
    }
    @Override
    public String toString() {
        return ("[" + this.type + "]" + (this.check ? "[X] " : "[ ] ") + this.task);
    }
}

