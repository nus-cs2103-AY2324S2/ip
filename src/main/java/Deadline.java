public class Deadline extends Task {

    private String by = null;

    public Deadline(String input) throws MissingInputFieldException {
        super(TaskType.DEADLINE);
        delimiter = "deadline|/by";
        command = "deadline";
        setUpTask(input);
    }

    @Override
    public String printType() {
        return "[D]";
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException {
        try {
            input = input.trim();
            if (!input.contains(command)) throw new RuntimeException("not deadline");
            String[] inputArray = Task.NextWords(input.split(delimiter));
            description = inputArray[0].trim();
            by = inputArray[1].trim();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return printType() + "[" + getIsDoneStatus() + "] "
                + description + " " + "(by: " + by + ")";
    }
}
