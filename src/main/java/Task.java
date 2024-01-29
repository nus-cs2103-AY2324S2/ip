public class Task {
    protected String type;
    protected String name;
    protected boolean isMarked;

    public Task() { type = "Task"; name = "task"; isMarked = false; }
    public Task(String _s, boolean _flag) { type = "Task"; name = _s; isMarked = _flag;} 

    public String Name() { return name; }
    public String Type() { return type; }
    public boolean IsMarked() { return isMarked; }
    public void UpdateMark(boolean _flag) { isMarked = _flag; }

    public String ToString() { 
        String s = "[";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + name;

        return s;
    }

    public String GetFileFormatParam() {
        return name;
    }
}