public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description, String from, String to){
        super(description);
        super.taskType = 'E';
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString(){
        String str = String.format(
                super.toString() + " (%s %s)", this.from, this.to);
        return str;
    }
}
