package goblin.task;

import goblin.calendar.Date;
import goblin.calendar.Time;
import goblin.exception.OrkException;
import goblin.task.Task;

public class Deadlines extends Task {
    protected Date date;
    protected Time time;
    protected String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        process(deadline);
    }

    public void process(String deadline) {
        String[] dateAndTime = deadline.split(" ");
        try {
            String date = dateAndTime[0];
            Date deadlineDate = new Date(date);
            Time deadlineTime;
            if (dateAndTime.length == 1) {
                deadlineTime = new Time("");
            } else if (dateAndTime.length == 2){
                deadlineTime = new Time(dateAndTime[1]);
            } else {
                throw new OrkException("What's the time? Can't believe you can't even do that right");
            }
            this.date = new Date(dateAndTime[0]);
            this.time = new Time(dateAndTime[1]);
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
        return isDone + " deadline " + getDescription() + " /by " + deadline;
    }

    @Override
    public String type() {
        return ("[D]");
    }
}
