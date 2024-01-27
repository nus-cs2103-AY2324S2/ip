import java.io.IOException;
import java.time.format.DateTimeParseException;

public abstract class Command {
    
    public boolean isExit;
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) 
    throws TaskCreationException, DateTimeParseException, IndexOutOfBoundsException,
    TaskModificationException, NumberFormatException, IOException;

}