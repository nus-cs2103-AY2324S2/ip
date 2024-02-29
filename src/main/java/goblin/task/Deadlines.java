package goblin.task;

import goblin.calendar.Date;
import goblin.calendar.Time;
import goblin.exception.OrkException;
import goblin.task.Task;

//Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
public class Deadlines extends Task {
    protected Date date;
    protected Time time;
    protected String deadline;

    /**
     * create a new deadlines object
     * @param description what the task is
     * @param deadline the deadline of the task
     */
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        process(deadline);
    }

    /**
     * transform the deadline from string to Date and Time objects
     * @param deadline a string deadline
     */
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

    /**
     * print the deadlines object
     */
    @Override
    public void print() {
        System.out.println("\t" + "[D]" + getStatusIcon() + getDescription() + "(by:"
                + date.toString() + " " + time.toString() + ")");
    }

    /**
     * return a string representing the deadline object
     * @return
     */
    @Override
    public String notPrint() {
        return "\t" + "[D]" + getStatusIcon() + getDescription() + "(by:"
                + date.toString() + " " + time.toString() + ")";
    }

    /**
     * toString function
     * @return a string
     */
    @Override
    public String toString() {
        return isDone + " deadline " + getDescription() + " /by " + deadline;
    }

    /**
     * a getter for the type
     * @return the type of this task
     */
    @Override
    public String type() {
        return ("[D]");
    }
}
