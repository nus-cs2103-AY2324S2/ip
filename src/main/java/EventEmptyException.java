public class EventEmptyException extends EmptyFieldException{
    private static final String missing_from_txt = "from - What time does %s start from?\n";
    private static final String missing_to_txt = "to - What time does %s end?\n";
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
