public class Events extends Task{
    protected String start;
    protected String end;
    
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    
    public String getStart() {
        return this.start;
    }
    
    public String getEnd() {
        return this.end;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + this.getStart() + " to " + this.getEnd() + ")";
    }
}
