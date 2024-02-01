public class Task {    
    public static int EXPECTED_FIELDS = 3;
    public String name;
    public boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.checkbox() + " " + this.name;
    }

    public String typeOfTask() {
        return " ";
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public String checkbox() {
        return String.format("[%s][%s]", this.typeOfTask(), this.getStatusIcon());
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isMarked() {
        return this.done;
    }

    private int isMarkedAsInt() {
        return (this.done) ? 1 : 0;
    }

    protected String[] exportDataAsArray() {
        String[] data = new String[]{(String)this.typeOfTask(), this.name, Integer.toString(this.isMarkedAsInt())};
        return data;
    }

    public String exportData() {
        String data = String.join(" ", this.exportData());
        return data;
    }
}