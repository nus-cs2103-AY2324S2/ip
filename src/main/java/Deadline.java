/**
 * Implementation of Task. Each Deadline comes with a description and a deadline.
 */

public class Deadline implements Task {

    protected String desc;
    protected String date;
    protected boolean checked;
    protected String type;

    public Deadline(String desc, String date){
        this.desc = desc;
        this.date = date;
        this.checked = false;
        this.type = "D";
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return "by:" + date;
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
        return type + " | " + temp + " | " + getDesc() + " | " + getDate() + "\n";
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc + "(" + getDate() + ")");
    }
}
