public class Events extends Task{
    private Task task;
    private String from;
    private String to;
    public Events(String name, String from, String to, Boolean status) {
        super(name, status);
        this.from = from;
        this.to = to;

    }

    @Override
    public String saveOutput() {
        return "E " + super.saveOutput() + String.format(" | %s-%s", from, to);
    }

    @Override
    public void taskInfo() {
        System.out.print("[E]");
        super.taskInfo();
        System.out.println(" (from: " + from + " to: " + to + ")");
    }
}
