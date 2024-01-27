/**
 * Implementation of Task. Each Event comes with a description, start and end date.
 */
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
        return "from: " + start;
    }

    public String getEnd() {
        return " to: " + end;
    }

    public String getCheck() {
        return checked ? "X" : " ";
    }

    public void setCheck(boolean x) {
        this.checked = x;
    }
    public String getType() {
        return this.type;
    }

    public String toSave() {
        String temp = checked ? "1" : "0";
        return type + " | " + temp + " | " + getDesc() + " | " + this.start + "-" + this.end + "\n";
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc + " ("+ getStart() + getEnd() +")");
    }
}
