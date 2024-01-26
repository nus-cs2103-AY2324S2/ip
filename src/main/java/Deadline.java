public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public void taskPrinter() {
        String result = "    " + "[D][ ]" + " " + description + "(by: " + deadline+ ")";
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        String result = "    " + (index+1) + ".[D]" + getStatusIcon() + " " + description  + "(by: " + deadline+ ")";
        System.out.println(result);
    }

    @Override
    public String storagePrinter() {
        return "D" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|by" + deadline;
    }
}
