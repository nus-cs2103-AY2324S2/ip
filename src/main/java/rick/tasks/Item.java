package rick.tasks;

public interface Item {
    String name = "";
    String status = "[ ]";

    public void mark();
    public void unmark();
    public String store();
}
