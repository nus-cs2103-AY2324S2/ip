import java.util.Arrays;

public class EventTask extends Task{
    protected String startBy;
    protected String endBy;
    public EventTask(String description, String startBy, String endBy) {
        super(description);
        this.startBy = startBy;
        this.endBy = endBy;
    }
    public static String[] getDescription(String[] details) {
        int fromIndex = -1;
        int toIndex = -1;
        for (int i = 0; i < details.length; i++) {
            if ("/from".equals(details[i])) {
                fromIndex = i;
            }
            if ("/to".equals(details[i])) {
                toIndex = i;
            }
        }

        String description = null;
        String startBy = null;
        String endBy = null;
        if (fromIndex != -1 && toIndex != -1 && toIndex - fromIndex > 1 && toIndex + 1 < details.length) {
            startBy = String.join(" ", Arrays.copyOfRange(details, fromIndex + 1, toIndex));
            endBy = String.join(" ", Arrays.copyOfRange(details, toIndex + 1, details.length));
            description = String.join(" ", Arrays.copyOfRange(details, 1, fromIndex));
        } else {
            System.out.println("Invalid event period");
        }

        return new String[]{description, startBy, endBy};
    }

    @Override
    public void updateDoneIcon() {
        super.updateDoneIcon();
    }
    @Override
    public String getDescription() {
        return super.getDescription();
    }
    @Override
    public String getDoneIcon() {
        return super.getDoneIcon();
    }
    public String getStartBy() {
        return this.startBy;
    }
    public String getEndBy() {
        return this.endBy;
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
        return "[E]" + super.toString() + " (from: " + getStartBy() + " to: " + getEndBy() + ")";
    }
}
