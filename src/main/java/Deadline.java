public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public void taskPrinter() {
        String result = "    " + "[D][ ]" + " " + description + "(by: " + deadline+ ")";
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        String result = "";
        if (isDone) {
            result = "    " + (index+1) + ".[D][X]" + " " + description + "(by: " + deadline+ ")";
        } else {
            result = "    " + (index+1) + ".[D][ ]" + " " + description + "(by: " + deadline+ ")";
        }

        System.out.println(result);
    }
}
