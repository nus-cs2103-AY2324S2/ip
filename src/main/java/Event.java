public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String name,boolean mark,String startDate,String endDate) {
        super(name,mark);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString(){
        return "[E] " + super.toString() + "("+this.startDate + this.endDate+")";
    }

    @Override
    public String toStringFile(){
        return "E|" + super.toStringFile() + "|"+this.startDate+"|"+this.endDate;
    }
}
