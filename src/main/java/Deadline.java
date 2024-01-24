public class Deadline implements Item {
    public String name;
    public String status = "[ ]";
    public String ddl;

    public Deadline(String name, String ddl) {
        this.name = name;
        this.ddl = ddl;
    }
    public String print(){
        return "[D]" + this.status + this.name + " (by: " + ddl + ")";
    };
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
}
