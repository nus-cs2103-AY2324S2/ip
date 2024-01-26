import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Command {




    public void execute(TaskList tasks, Ui ui, Storage storage) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }


    public boolean isExit() {
        return false;
    }
}
