import java.util.Arrays;

public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
    }
    public static String getDescription(String[] details) throws DukeException{
        String desc = String.join(" ", Arrays.copyOfRange(details, 1, details.length));
        if (details.length == 1) {
            throw new DukeException("No description of task given, please try again!");
        }
        return desc;
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
        return "T" + " | " + super.toString();
    }


}
