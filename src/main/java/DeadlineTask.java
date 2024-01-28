import java.util.Arrays;

public class DeadlineTask extends Task{
    protected String doneBy;

    public DeadlineTask(String description, String doneBy) {
        super(description);
        this.doneBy = doneBy;
    }

    @Override
    public void updateDoneIcon() {
        super.updateDoneIcon();
    }
    public static String[] getDescription(String[] details) {
        int byIndex = -1;
        for (int i = 0; i < details.length; i++) {
            if ("/by".equals(details[i])) {
                byIndex = i;
                break;
            }
        }

        String description = null;
        String deadlineDate = null;
        if (byIndex != -1 && byIndex + 1 < details.length) {
            deadlineDate = String.join(" ", Arrays.copyOfRange(details, byIndex + 1, details.length));
            description = String.join(" ", Arrays.copyOfRange(details, 1, byIndex));
        } else {
            System.out.println("No deadline date found.");
        }
        return new String[]{description, deadlineDate};
    }
    @Override
    public String getDoneIcon() {
        return super.getDoneIcon();
    }
    public String getDoneBy() {
        return this.doneBy;
    }
    @Override
    public void markDone() {
        super.markDone();
    }
    @Override
    public void markNotDone() {
        super.markNotDone();;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDoneBy() + ")";
    }
}
