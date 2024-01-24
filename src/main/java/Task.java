public class Task {
    protected String description;
    protected boolean checked;

    Task (String desc) {
        this.description = desc;
        this.checked = false;
    }

    public String getDesc() {
        return description;
    }

    public String getCheck() {
        String x = " ";
        if (checked) x = "X";
        return ("[" + x + "]");
    }

    public void setCheck(boolean x){
        checked = x;
    }

    @Override
    public String toString(){
        return getCheck() + " " + getDesc();
    }
}
