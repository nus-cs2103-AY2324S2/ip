public class Deadlines extends Task{
    private Task task;
    private String by;
    public Deadlines(String name, String by, Boolean status) {
        super(name, status);
        this.by = by;
    }

    @Override
    public String saveOutput() {
        return "D " + super.saveOutput() + String.format(" | %s", by);
    }

    @Override
    public void taskInfo() {
        System.out.print("[D]");
        super.taskInfo();
        System.out.println(" (by: " + by + ")");
    }
}
