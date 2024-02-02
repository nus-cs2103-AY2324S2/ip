import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime from = null;
    private LocalDateTime to = null;

    public Event(String input) throws MissingInputFieldException {
        super(TaskType.EVENT);
        delimiter = "event|/from|/to";
        command = "event";
        setUpTask(input);
    }

    @Override
    public String printType() {
        return "E";
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException {
        try {
            input = input.trim();
            if (!input.contains(command)) throw new RuntimeException("not todo");
            String[] inputArray = Task.NextWords(input.split(delimiter));
            description = inputArray[0].trim();
            from = Parser.parseDateAndTime(inputArray[1].trim());
            to = Parser.parseDateAndTime(inputArray[2].trim());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return "[" + printType() + "]" + "[" + getIsDoneStatus() + "] "
                + description + " " + "(from: "
                + from + " to: " + to + ")";
    }

    @Override
    public String convertToDataRow() {
        return super.convertToDataRow() + storageDataStringSplitter + Storage.convertDateTimeForStorage(from) +
                storageDataStringSplitter + Storage.convertDateTimeForStorage(to);
    }
}