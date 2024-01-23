public class Todo extends Task {
    protected static String keyword = "todo";
    private Todo(String description) {
        super(description);
    }

    public static Todo of(String input) {
        try {
            if (!startsWith(keyword, input)) {
                throw new BobException("OH NOSE! This input is not todo..");
            }
            if (keyword.length() + 1 >= input.length()) {
                throw new BobException("OH NOSE! The description of todo cannot be empty..");
            }
            return new Todo(input.substring(keyword.length() + 1));

        } catch (BobException e) {
                System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}