public class TodoTask extends Task {
    private String type;

    public TodoTask(String what) {
        super(what);
        this.type = "[T]";
    }

    public String showAll() {
        return this.type + super.showAll();
    }
}
