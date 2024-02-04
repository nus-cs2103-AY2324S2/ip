public class Event extends Task{

    private String start;
    private String end;
    public Event(String name, String start, String end, boolean isDone){
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){

        return "[E]" + super.toString() +  "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String header(){

        int binary = super.isDone? 1: 0;
        return this.type() + binary;
    }

    @Override
    public String type(){

        return "E";
    }

    @Override
    public String additionalInfo(){

        return "/" + start + "/" + end;
    }
}
