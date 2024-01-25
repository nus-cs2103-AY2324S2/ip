public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event() { name = "event"; startDate = "(NOW)"; endDate = "(NOW)"; isMarked = false; }
    public Event(String _s, boolean _flag) {
        name = _s.substring(0, _s.indexOf(" /from "));
        
        String n = _s.substring(_s.indexOf(" /from ") + 7);
        startDate = n.substring(0, n.indexOf(" /to "));
        endDate = n.substring(n.indexOf(" /to ") + 5);
        System.out.println("DEDDDED " + startDate);
        System.out.println("ddd " + _s.substring(_s.indexOf(" /from")));
        isMarked = _flag;
    } 

    @Override
    public String ToString() { 
        String s = "[E][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + name + " (from: " + startDate + " to: " + endDate + ")";

        return s;
    }
}