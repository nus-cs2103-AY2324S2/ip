package jav.task;

import jav.exception.InvalidParamException;

/**
* Event is a task that also consists of a start and end date.
*/
public class Event extends Task {
    /** Non-date formatted start date. */
    protected String startDate;

    /** Non-date formatted end date. */
    protected String endDate;
    
    /**
     * Constructs a new Event.
     *
     * @return a new Event.
     */
    public Event() {
        type = "Event";
        description = "event";
        startDate = "(NOW)";
        endDate = "(NOW)";
        isMarked = false;
    
    }
    
    /**
     * Constructs a new Event.
     *
     * @param params a string containing the information about the event.
     * @param isMarked whether the event is marked.
     *
     * @return a new Event.
     * 
     * @throws InvalidParamException if the parameters are invalid.
     */
    public Event(String params, boolean isMarked) throws InvalidParamException {
        // Check for invalid params
        if (!params.contains(" /from ") || (!params.contains(" /to "))) {
            throw new InvalidParamException("Invalid param for event", null);
        }

        // Getting the tokens
        int start = params.indexOf(" /from ");
        int end = params.indexOf(" /to ");
        if (end < start) {
            throw new InvalidParamException("Invalid param for event", null);
        }
        type = "Event";
        description = params.substring(0, start);
        String n = params.substring(start + 7);
        
        // Set the event values
        startDate = n.substring(0, n.indexOf(" /to "));
        endDate = n.substring(n.indexOf(" /to ") + 5);
        this.isMarked = isMarked;
    } 

    @Override
    public String toString() { 
        String s = "[E][";
        if (isMarked) {
            s += "X";
        } else {
            s += " ";
        }
        s += "] " + description + " (from: " + startDate + " to: " + endDate + ")";

        return s;
    }

    @Override
    public String getFileFormatParam() {
        return description + " /from " + startDate + " /to " + endDate;
    }
}