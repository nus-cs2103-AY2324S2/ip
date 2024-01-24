public class Deadline implements Task {

    protected String desc;
    protected String date;
    protected boolean checked;
    protected String type;

    public Deadline(String params){
        this.desc = params.split("/by")[0];
        this.date = params.split("/by")[1];
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
