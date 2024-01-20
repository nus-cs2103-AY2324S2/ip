public class Add implements Command {
    private String task;

    public Add(String task) {
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public void print() {
        System.out.println(String.format("added: %s", this.getTask()));
    }
}
