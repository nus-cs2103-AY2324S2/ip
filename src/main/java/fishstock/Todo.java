package fishstock;

class Todo extends Task {
    protected final static String keyword = "todo";

    protected Todo(String description) {
        super(description);
    }

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