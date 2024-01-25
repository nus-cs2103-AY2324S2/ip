public class list_Entry {
    String task;
    boolean check;

    public list_Entry(String t, boolean c) {
        this.task = t;
        this.check = c;
    }

    public void markEntry () {
        this.check = true;
    }

    public void unmarkEntry() {
        this.check = false;
    }
    @Override
    public String toString() {
        return (this.check ? "[X] " : "[ ] ") + this.task;
    }
}
