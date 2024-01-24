public class Event implements Item{
    public String name = "";
    public String status = "[ ]";
    public String from = "";
    public String to = "";

    public Event(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }
    public String print(){
        return "[E]" + this.status + this.name + " (from: " + this.from + " to: " + this.to + ")";
    };
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
}
