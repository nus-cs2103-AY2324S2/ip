package tasks;

class Deadline extends Task {  // default access modifier
    private static final String DEADLINE_ICON = "[D]";
    private String by;

    Deadline(String description, String by) {  // default access modifier
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + by + ")";
    }
}
