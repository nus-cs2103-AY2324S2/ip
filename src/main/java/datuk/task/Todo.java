package datuk.task;

/**
 * Implementation of Task. Each Todo comes with a description.
 */
public class Todo implements Task {

    protected String desc;
    protected boolean isMarked;
    protected String type;

    public Todo(String params){
        this.desc = params;
        isMarked = false;
        type = "T";
    }

    public String getDesc() {
        return desc;
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
        return type + " | " + temp + " | " + getDesc() + "\n";
    }

    @Override
    public String toString() {
        return ("[" + type + "][" + getCheck() + "] " + desc);
    }
}
