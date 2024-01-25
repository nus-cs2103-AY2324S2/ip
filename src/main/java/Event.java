public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String desc,int type, String startDate, String endDate){
        super(desc, type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return super.toString() + "(" + startDate + endDate + ")";
    }
}
