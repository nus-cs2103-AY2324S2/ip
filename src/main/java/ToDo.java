public class ToDo implements Item {
    public String name;
    public String status = "[ ]";

    public ToDo(String name) {
        this.name = name;
    }
    public String print() {
        return "[T]" + this.status + this.name;
    }
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
}
