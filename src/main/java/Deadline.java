public class Deadline extends Task {
    protected static String keyword = "deadline";
    protected static String byKeyword = " /by ";
    protected String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline of(String input) {
        try {
            if (!startsWith(keyword, input)) {
                throw new BobException("OH NOSE! This input is not deadline..");
            }
            int byIdx = input.indexOf(byKeyword);
            if (byIdx == -1) {
                throw new BobException("OH NOSE! \"" + byKeyword + "\" not found..");
            }
            if (keyword.length() + 1 > byIdx) {
                throw new BobException("OH NOSE! The description of deadline cannot be empty..");
            }
            if (byIdx + byKeyword.length() == input.length()) {
                throw new BobException("OH NOSE! The by-date cannot be empty..");
            }
            String description = input.substring(keyword.length() + 1, byIdx);
            String by = input.substring(byIdx + byKeyword.length());
            return new Deadline(description, by);

        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}