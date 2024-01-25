public class list_Entry {
    public static final String TYPE_TODO = "[T]";
    public static final String TYPE_DEADLINE = "[D]";
    public static final String TYPE_EVENT = "[E]";


    protected String task;
    protected boolean check;
    protected String type;
    protected String task_start;
    protected String task_end;

    public list_Entry() {
    }
    public list_Entry(String task, boolean check, String type, String tStart, String tEnd) {
        this.task = task;
        this.check = check;
        this.type = type;
        this.task_start = tStart;
        this.task_end = tEnd;
    }
    public list_Entry(String task, boolean check, String type) {
        this.task = task;
        this.check = check;
        this.type = type;
    }



    public void markEntry () {
        this.check = true;
    }

    public void unmarkEntry() {
        this.check = false;
    }
    @Override
    public String toString() {
        return (this.type + (this.check ? "[X] " : "[ ] ") + this.task);
    }
}

