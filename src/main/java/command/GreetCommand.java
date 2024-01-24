package command;

import duke.Storage;
import view.GreetView;

/**
 * Command to greet user when program is started.
 */
public class GreetCommand extends Command {
    private final GreetView greetView;

    /**
     * Constructs a {@code GreetCommand}.
     */
    public GreetCommand() {
        this.greetView = new GreetView();
    }

    /**
     * Executes the {@code GreetCommand}, print a welcome message greeting the user.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@code GreetCommand}
     */
    @Override
    public void execute(Storage storage) {
        greetView.display();
    }
}
