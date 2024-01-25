public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String desc, String startDate, String endDate){
        super(desc);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startDate + endDate + ")";
    }
}
