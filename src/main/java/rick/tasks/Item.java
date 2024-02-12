package rick.tasks;

public interface Item {
    public String name = "";
    public String status = "[ ]";

    public void mark();
    public void unmark();
    public String store();
}
