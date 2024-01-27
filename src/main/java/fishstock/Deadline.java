package fishstock;

import java.time.LocalDateTime;

class Deadline extends Task {
    protected final static String keyword = "deadline";
    private final static String byKeyword = " /by ";
    private LocalDateTime by;

    protected Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    protected static Deadline of(String input) throws FishStockException {
        if (!Parser.startsWith(keyword, input)) {
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
        String byStr = input.substring(byIdx + byKeyword.length());
        LocalDateTime by = Parser.parseDate(byStr);
        return new Deadline(description, by);
    }

    @Override
    protected String toSaveString() {
        return "D|" + description + "|" + Parser.inDate(by) +
                "|" + boolToInt(isDone) + System.lineSeparator();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.outDate(by) + ")";
    }
}