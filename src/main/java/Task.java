public class Task {
    private String name;
    private Boolean mark;
    public Task(String name) {
        this.name = name;
        this.mark = false;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.mark ? "X" : " ", this.name);
    }
}
