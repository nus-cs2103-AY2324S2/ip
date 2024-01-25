public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void taskPrinter() {
        String result = "    " + "[E][ ]" + " " + description + "(from: " + from + " to: " + to +")";
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        String result = "";
        if (isDone) {
            result = "    " + (index+1) + ".[E][X]" + " " + description + "(from: " + from + " to: " + to +")";
        } else {
            result = "    " + (index+1) + ".[E][ ]" + " " + description + "(from: " + from + " to: " + to +")";
        }

        System.out.println(result);
    }
}
