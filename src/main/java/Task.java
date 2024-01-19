public abstract class Task {
    private String name;
    private boolean marked = false;
    public Task(String name) {
        this.name = name;
    }

    public void setDone() {
        this.marked = true;
        System.out.println("Thats sick! Great work, marked as done!\n" + this.toString());
    }

    public void setNotDone() {
        this.marked = false;
        System.out.println("Awh why uncheck me :( Its ok, it is what it is!\n" + this.toString());
    }

    @Override
    public String toString() {
        String mark = this.marked ? "[X] " : "[ ] ";
        return mark + this.name;
    }
}