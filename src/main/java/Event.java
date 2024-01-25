public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event() { name = "event"; startDate = "(NOW)"; endDate = "(NOW)"; isMarked = false; }
    public Event(String _s, boolean _flag) {
        // Check for invalid params
        if (!_s.contains(" /from ") || (!_s.contains(" /to "))) {
            throw new InvalidParamException("Invalid param for event", null);
        }
        int a = _s.indexOf(" /from ");
        int b = _s.indexOf(" /to ");
        
        if (b < a) {
            throw new InvalidParamException("Invalid param for event", null);
        }

        name = _s.substring(0, a);
        
        String n = _s.substring(a + 7);
        startDate = n.substring(0, n.indexOf(" /to "));
        endDate = n.substring(n.indexOf(" /to ") + 5);
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