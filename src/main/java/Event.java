public class Event extends Task{
    private String start;
    private String end;
    public Event (String s, String start, String end){
        super(s);
        this.start = start.substring(0, 4) + ":" + start.substring(4);
        this.end = end.substring(0, 2) + ":" + end.substring((2));
    }

    public Event (String s, boolean mark, String start, String end){
        super(s);
        this.start = start.substring(0, 4) + ":" + start.substring(4);
        this.end = end.substring(0, 2) + ":" + end.substring((2));
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }
    @Override
    public String toString(){
        String X = this.getMark() ? "X" : " ";
        return "[E]"+"[" + X + "] " + this.getItem()
                + " (" + start + " " + end + ")";
    }
}
