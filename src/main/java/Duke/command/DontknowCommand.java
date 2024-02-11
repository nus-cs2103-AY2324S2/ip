package Duke.command;
import Duke.*;
import Duke.task.*;
/**
 * Represent command of showing user that this program don't understand s/he command.
 */
public class DontknowCommand extends Command {
    /**
     * Display message to user to tell that the program don't know what user's input means
     * @param tsks the task list potentially be worked on, not used in this program
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally, not used in this program
     */
    public void execute(TaskList tsks, Ui ui, Storage storage){
        Ui.print_message("OOPS!!! I'm sorry, but I don't know what that means");
    }
    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    @Override
    public boolean isExit(){
        return false;
    }
}
