public class Event implements Item{
    public String name;
    public String status;
    public String from;
    public String to;

    public Event(String name, String status, String from, String to) throws RickException{
        if (name.isBlank()) {
            throw new RickException("Nothing scheduled?");
        }
        if (from.isBlank()) {
            throw new RickException("from when?");
        }
        if (to.isBlank()) {
            throw new RickException("to when?");
        }
        this.name = name;
        this.status = status;
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString(){
        return "[E]" + this.status + " " + this.name + " (from: " + this.from + " to: " + this.to + ")";
    };
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
    public String store() {
        return "E|" + this.status + "|" + this.name + "|" + this.from + "|" + this.to;
    }
}
