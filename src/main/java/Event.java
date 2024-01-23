

/* Events: tasks that start at a specific date/time and
* ends at a specific date/time e.g.,
* (a) event team project meeting /from 2-10-2019 /to 2-4pm
* (b) event orientation week /from 4-10-2019 /to 11-10-2019
* */
public class Event extends Task {

    private String startDate;

    private String endDate;

    public Event (String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // project meeting (from: Aug 6th 2pm to: 4pm)
    @Override
    public String listTaskString() {
        return "[E]" + super.listTaskString()
                + " (from: " + startDate
                + " to: " + endDate + ")";
    }



}
