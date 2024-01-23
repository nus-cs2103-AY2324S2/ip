public class Deadline extends Task {
    protected static String keyword = "deadline";
    protected static String byKeyword = " /by ";
    protected String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline of(String input) {
        if (!startsWith(keyword, input)) {
            System.out.println("OH NOSE! This input is not deadline..");
            return null;
        }
        int byIdx = input.indexOf(byKeyword);
        if (byIdx == -1) {
            System.out.println("OH NOSE! \"" + byKeyword + "\" not found..");
            return null;
        }
        if (keyword.length() + 1 > byIdx) {
            System.out.println("OH NOSE! The description of deadline cannot be empty..");
            return null;
        }
        if (byIdx + byKeyword.length() == input.length()) {
            System.out.println("OH NOSE! The by-date cannot be empty..");
            return null;
        }

        String description = input.substring(keyword.length() + 1, byIdx);
        String by = input.substring(byIdx + byKeyword.length());
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}