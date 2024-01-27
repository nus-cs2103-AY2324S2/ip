package fishstock;

class Todo extends Task {
    protected final static String keyword = "todo";
    protected Todo(String description) {
        super(description);
    }

    protected static Todo of(String input) {
        try {
            if (!startsWith(keyword, input)) {
                throw new FishStockException("OH NOSE! This input is not todo..");
            }
            if (keyword.length() + 1 >= input.length()) {
                throw new FishStockException("OH NOSE! The description of todo cannot be empty..");
            }
            return new Todo(input.substring(keyword.length() + 1));

        } catch (FishStockException e) {
                System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}