import java.util.Arrays;

public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
    }
    public static String getDescription(String[] details) {

        return String.join(" ", Arrays.copyOfRange(details, 1, details.length));
    }
    @Override
    public void updateDoneIcon() {
        super.updateDoneIcon();
    }

    @Override
    public String getDoneIcon() {
        return super.getDoneIcon();
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
        return "[T]" + super.toString();
    }


}
