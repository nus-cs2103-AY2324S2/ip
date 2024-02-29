package goblin.task;

import goblin.calendar.Date;
import goblin.calendar.Time;
import goblin.exception.OrkException;

//Solution below inspired by https://github.com/nus-cs2103-AY1920S1/duke/pull/23/commits
public class Events extends Task {
    protected Date startDate;
    protected Date endDate;
    protected Time endTime;
    protected Time startTime;
    public String start;
    public String end;

    /**
     * create a new event object
     * @param description what the task is
     * @param start the start date and time
     * @param end the end date and time
     */
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        processs(start);
        processe(end);
    }

    /**
     * transform the string start date and time to objects of Dats and Times
     * @param time the start date and time string
     */
    public void processs(String time) {
        String[] splitTD = time.split(" ");
        try {
            if (splitTD.length == 2) {
                this.startTime = new Time(splitTD[1]);
            } else if (splitTD.length > 2) {
                throw new OrkException("Follow the format!");
            }
            this.startDate = new Date(splitTD[0]);
            this.startTime = new Time(splitTD[1]);
        } catch (OrkException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * transform the string end date and time to objects of Dats and Times
     * @param time the end date and time string
     */
    public void processe(String time) {
        String[] splitTD = time.split(" ");
        try {
            this.endDate = new Date(splitTD[0]);
            this.endTime = new Time(splitTD[1]);
        } catch (OrkException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * print the event
     */
    @Override
    public void print () {
        System.out.println("\t" + "[E]" + getStatusIcon() + getDescription() + "(from:" + startDate.toString()
                + " " + startTime.toString() + " to " + endDate.toString() + " " + endTime.toString() + ")");
    }

    /**
     * a string representation of the event
     * @return a string
     */
    @Override
    public String notPrint () {
        return "\t" + "[E]" + getStatusIcon() + getDescription() + "(from:" + startDate.toString()
                + " " + startTime.toString() + " to " + endDate.toString() + " " + endTime.toString() + ")";
    }

    /**
     * a toString method
     * @return a string
     */
    @Override
    public String toString () {
        return isDone + " event " + getDescription() + " /from " + start + " /to " + end;
    }

    /**
     * a getter for the type
     * @return the type of this task
     */
    @Override
    public String type () {
        return ("[E]");
    }
}