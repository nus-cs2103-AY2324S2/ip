package datuk.task;

/**
 * Implementation of Task. Each Event comes with a description, start and end date.
 */
public class Event implements Task {

    protected String desc;
    protected String start;
    protected String end;
    protected boolean isMarked;
    protected String type;

    public Event(String desc, String start, String end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
        isMarked = false;
        type = "E";
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
        return isMarked ? "X" : " ";
    }

    public void setCheck(boolean x) {
        isMarked = x;
    }
    public String getType() {
        return type;
    }

    public String toSave() {
        String temp = isMarked ? "1" : "0";
        return type + " | " + temp + " | " + getDesc() + " | " + start + "-" + end + "\n";
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc + " ("+ getStart() + getEnd() +")");
    }
}
