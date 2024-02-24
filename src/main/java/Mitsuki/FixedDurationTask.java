package Mitsuki;


public class FixedDurationTask extends Task {
    protected String duration;
    public FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Converts the FixedDurationTask object into a human-readable String to be displayed to the user.
     * @return String object that represents the FixedDurationTask object.
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (Needs " + duration + ")";
    }
}
