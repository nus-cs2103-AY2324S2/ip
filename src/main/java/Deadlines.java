public class Deadlines extends Task{
    protected Date date;
    protected Time time;

    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        process(deadline);
    }

    public void process(String deadline) {
        String[] splitTD = deadline.split(" ");
        try {
            this.date = new Date(splitTD[0]);
            this.time = new Time(splitTD[1]);
        } catch (OrkException e) {

        }
    }

    @Override
    public void print() {
        System.out.println("\t" + "[D]" + getStatusIcon() + getDescription() + "(by:"
                + date.toString() + " " + time.toString() + ")");
    }

    @Override
    public String notPrint() {
        return "\t" + "[D]" + getStatusIcon() + getDescription() + "(by:"
                + date.toString() + " " + time.toString() + ")";
    }

    @Override
    public String toString() {
        return isDone + " deadline " + getDescription() + "/by " + deadline;
    }

    @Override
    public String type() {
        return ("[D]");
    }
}
