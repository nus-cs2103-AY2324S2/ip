public class Task {
    protected int num;
    protected String description;
    protected boolean isDone;

    public Task(int num, String description) {
        this.num = num;
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        String check = "[ ] ";
        if (isDone) {
            check = "[X] ";
        }

        return check + description;
    }

    public void mark(boolean status) {
        isDone = status;
    }

}
