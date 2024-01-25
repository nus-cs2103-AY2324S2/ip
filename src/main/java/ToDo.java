public class ToDo extends Task {
    public ToDo() { name = "todo"; isMarked = false; }
    public ToDo(String _s, boolean _flag) { name = _s; isMarked = _flag;} 

    @Override
    public String ToString() { 
        String s = "[T][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + name;

        return s;
    }
}