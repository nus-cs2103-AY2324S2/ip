public class Todo extends Task {
    protected static String keyword = "todo";
    private Todo(String description) {
        super(description);
    }

    public static Todo of(String input) {
        if (!startsWith(keyword, input)) {
            System.out.println("OH NOSE! This input is not todo..");
            return null;
        }
        if (keyword.length() + 1 >= input.length()) {
            System.out.println("OH NOSE! The description of todo cannot be empty..");
            return null;
        }
        return new Todo(input.substring(keyword.length() + 1));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}