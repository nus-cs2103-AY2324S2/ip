import java.util.Arrays;
import exceptions.InvalidCommandException;
public class ToDoTask extends Task{
    public ToDoTask(String description) {
        super(description);
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
