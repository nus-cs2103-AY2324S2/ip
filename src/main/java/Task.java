public class Task {
    protected String name;
    protected boolean isMarked;

    public Task() { name = "task"; isMarked = false; }
    public Task(String _s, boolean _flag) { name = _s; isMarked = _flag;} 

    public String Name() { return name; }
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
}