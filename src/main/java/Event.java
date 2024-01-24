public class Event implements Task {

    protected String desc;
    protected String start;
    protected String end;
    protected boolean checked;
    protected String type;

    public Event(String desc, String start, String end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
        this.checked = false;
        this.type = "E";
    }

    public String getDesc() {
        return desc;
    }

    public String getStart() {
        return "from:" + start;
    }

    public String getEnd() {
        return "to:" + end;
    }

    public String getCheck() {
        return checked ? "X" : " ";
    }

    public void setCheck(boolean x) {
        this.checked = x;
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc + "("+ getStart() + getEnd() +")");
    }
}
