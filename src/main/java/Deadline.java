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

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc + "(" + getDate() + ")");
    }
}
