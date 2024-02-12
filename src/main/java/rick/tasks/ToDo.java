package rick.tasks;
import rick.RickException;

public class ToDo implements Item {
    private String name;
    private String status;

    public ToDo(String name, String status) throws RickException {
        if (name.isBlank()) {
            throw new RickException("Nothing to do!");
        }
        this.name = name;
        this.status = status;
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
    public String store() {
        return "T|" + this.status + "|" + this.name;
    }
}
