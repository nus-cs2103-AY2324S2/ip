public class Events extends Task{
    private Task task;
    private String from;
    private String to;
    public Events(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;

    }

    @Override
    public void taskInfo() {
        System.out.print("[E]");
        super.taskInfo();
        System.out.println(" (from: " + from + " to: " + to + ")");
    }
}
