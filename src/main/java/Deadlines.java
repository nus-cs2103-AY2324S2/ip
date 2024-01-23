public class Deadlines extends Task{
    private Task task;
    private String by;
    public Deadlines(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public void taskInfo() {
        System.out.print("[D]");
        super.taskInfo();
        System.out.println(" (by: " + by + ")");
    }
}
