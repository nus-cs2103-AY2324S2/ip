public class Event extends Task {

    private String from = null;
    private String to = null;

    public Event(String input) throws MissingInputFieldException {
        super(TaskType.EVENT);
        delimiter = "event|/from|/to";
        command = "event";
        setUpTask(input);
    }

    @Override
    public String printType() {
        return "[E]";
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException {
        try {
            input = input.trim();
            if (!input.contains(command)) throw new RuntimeException("not todo");
            String[] inputArray = Task.NextWords(input.split(delimiter));
            description = inputArray[0].trim();
            from = inputArray[1].trim();
            to = inputArray[2].trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return printType() + "[" + getIsDoneStatus() + "] "
                + description + " " + "(from: "
                + from + " to: " + to + ")";
    }
}