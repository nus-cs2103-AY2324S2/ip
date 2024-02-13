package fishstock;

/**
 * Encapsulates Todo Task.
 * This Task only has a description.
 */
class Todo extends Task {
    protected static final String COMMAND = "todo";

    /**
     * Initialize Todo object manually.
     * @param description The task description.
     */
    protected Todo(String description) {
        super(description);
    }

    private static void checkIsValid(String input) throws FishStockException {
        if (!Parser.startsWith(COMMAND, input)) {
            throw new FishStockException("OH NOSE! This input is not todo..");
        }
        if (COMMAND.length() + 1 >= input.length()) {
            throw new FishStockException("OH NOSE! The description of todo cannot be empty..");
        }
    }

    /**
     * Initialize Todo object from input.
     * Has format "todo [description]".
     * @param input The input from user.
     * @return The generated Todo object.
     * @throws FishStockException The exceptions while creating the Todo object.
     */
    protected static Todo of(String input) throws FishStockException {
        checkIsValid(input);
        return new Todo(input.substring(COMMAND.length() + 1));
    }

    @Override
    protected String toSaveString() {
        return "T|" + getDescription() + "|" + toSaveIsDone() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
