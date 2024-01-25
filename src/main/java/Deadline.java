public class Deadline extends Task {
    protected String dueDate;

    public Deadline() { name = "deadline"; dueDate = "(NOW)"; isMarked = false; }
    public Deadline(String _s, boolean _flag) {
        name = _s.substring(0, _s.indexOf(" /by "));
        dueDate = _s.substring(_s.indexOf(" /by ") + 5);
        isMarked = _flag;
    } 

    @Override
    public String ToString() { 
        String s = "[D][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + name + " (by: " + dueDate + ")";

        return s;
    }
}