package fishstock;

/**
 * Encapsulates Todo Task.
 * This Task only has a description.
 */
class Todo extends Task {
    protected final static String keyword = "todo";

    /**
     * Initialize Todo object manually.
     * @param description The task description.
     */
    protected Todo(String description) {
        super(description);
    }

    /**
     * Initialize Todo object from input.
     * Has format "todo [description]".
     * @param input The input from user.
     * @return The generated Todo object.
     * @throws FishStockException The exceptions while creating the Todo object.
     */
    protected static Todo of(String input) throws FishStockException {
        if (!Parser.startsWith(keyword, input)) {
            throw new FishStockException("OH NOSE! This input is not todo..");
        }
        if (keyword.length() + 1 >= input.length()) {
            throw new FishStockException("OH NOSE! The description of todo cannot be empty..");
        }
        return new Todo(input.substring(keyword.length() + 1));
    }

    @Override
    protected String toSaveString() {
        return "T|" + description + "|" + boolToInt(isDone) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}