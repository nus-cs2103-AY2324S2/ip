public class ToDo implements Item {
    public String name;
    public String status = "[ ]";

    public ToDo(String name) throws RickException {
        if (name.isBlank()) {
            throw new RickException("Nothing to do!");
        }
        this.name = name;
    }
    @Override
    public String toString(){
        return "[T]" + this.status + " " + this.name;
    }
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
}
