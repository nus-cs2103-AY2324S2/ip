public class list_Entry_Event extends list_Entry {
    public list_Entry_Event(String task, boolean check, String tStart, String tEnd) {
        super(task, check, TYPE_EVENT, tStart, tEnd);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: "+ this.task_start + " to: " + this.task_end + ")";
    }
}
