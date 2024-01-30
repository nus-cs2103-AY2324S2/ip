import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Command {

    public boolean isExit;

    public Command() {
        isExit = false;
    }

    public abstract void excuteCommand(TaskList tasks,Ui ui, Storage storage) throws DukeException;

    public void confirmExit() {
        this.isExit = true;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

}
