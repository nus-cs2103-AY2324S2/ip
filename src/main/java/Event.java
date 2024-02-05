/**
 * class that represents an event type task 
 */
public class Event extends Task{
    private String startTime;
    private String endTime;
    public Event(String taskString, String startTime, String endTime) {
        super(taskString);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String taskString, String startTime, String endTime, boolean isDone) {
        super(taskString, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " | " + startTime
                + " | " + endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event event = (Event) obj;
        return (super.equals(event) && this.startTime.equals(event.startTime)
                && this.endTime.equals(event.endTime));
    }
    
}
