public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + this);
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("    " + this);
    }

    public String getIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }
    public String saveToFileString() {
        int doned = this.isDone ? 1 : 0;
        return doned + " | " + this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getIcon() + "] " + this.name;
    }
}
