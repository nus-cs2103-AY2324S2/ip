package fishstock;

class Deadline extends Task {
    protected final static String keyword = "deadline";
    private final static String byKeyword = " /by ";
    private String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    protected static Deadline of(String input) {
        try {
            if (!startsWith(keyword, input)) {
                throw new FishStockException("OH NOSE! This input is not deadline..");
            }
            int byIdx = input.indexOf(byKeyword);
            if (byIdx == -1) {
                throw new FishStockException("OH NOSE! \"" + byKeyword + "\" not found..");
            }
            if (keyword.length() + 1 > byIdx) {
                throw new FishStockException("OH NOSE! The description of deadline cannot be empty..");
            }
            if (byIdx + byKeyword.length() == input.length()) {
                throw new FishStockException("OH NOSE! The by-date cannot be empty..");
            }
            String description = input.substring(keyword.length() + 1, byIdx);
            String by = input.substring(byIdx + byKeyword.length());
            return new Deadline(description, by);

        } catch (FishStockException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}