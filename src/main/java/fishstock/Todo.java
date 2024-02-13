package fishstock;

/**
 * Encapsulates Todo Task.
 * This Task only has a description.
 */
class Todo extends Task {
    /**
     * Initialize Todo object manually.
     * @param description The task description.
     */
    protected Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    protected Todo(String description) {
        this(description, false);
    }

    private static void checkIsValid(String[] splitInput) throws FishStockException {
        if (splitInput[0].isEmpty()) {
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
    protected static Todo of(UserInput input) throws FishStockException {
        assert input.getCommandType() == Command.CommandType.TODO : "The input type is not Todo";

        String[] splitInput = input.splitByKeywords();
        checkIsValid(splitInput);

        String description = splitInput[0];
        return new Todo(description);
    }

    @Override
    protected Task clone() {
        return new Todo(this.getDescription(), this.getIsDone());
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
