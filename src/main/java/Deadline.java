public class Deadline extends Item {
    private String givenDeadline;
    public Deadline(String description, String givenDeadline) {
        super(description);
        this.givenDeadline = givenDeadline;
    }

    /**
     * Returns the description of this deadline.
     *
     * @return Description of this deadline.
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + givenDeadline + ")";
    }
}
