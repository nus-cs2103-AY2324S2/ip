package dukeexceptions;

/** Exception when an empty field is passed for a field*/
public class EventEmptyException extends EmptyFieldException{
    /** text for when from entry is missing */
    private static final String missing_from_txt = "from - What time does %s start from?\n";
    /** text for when to entry is missing */
    private static final String missing_to_txt = "to - What time does %s end?\n";

    /**
     * Checks which field is missing and informs user of missing field
     *
     * @param event
     * @param missing_from
     * @param missing_to
     */
    public EventEmptyException(String event, boolean missing_from, boolean missing_to) {
        super((missing_from && missing_to)
                ? String.format(EventEmptyException.missing_from_txt, event) + String.format(EventEmptyException.missing_to_txt, event)
                : (missing_from)
                ? String.format(EventEmptyException.missing_from_txt, event)
                : (missing_to)
                ? String.format(EventEmptyException.missing_to_txt, event)
                : "");
    }
}
