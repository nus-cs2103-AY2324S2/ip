/**
 * Implementation of Task. Each Todo comes with a description.
 */
public class Todo implements Task {

    protected String desc;
    protected boolean checked;
    protected String type;

    public Todo(String params){
        this.desc = params;
        this.checked = false;
        this.type = "T";
    }

    public String getDesc() {
        return desc;
    }

    public String getCheck() {
        return checked ? "X" : " ";
    }

    public void setCheck(boolean x) {
        this.checked = x;
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc);
    }
}
