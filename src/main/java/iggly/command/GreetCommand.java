package iggly.command;

import iggly.duke.Storage;
import iggly.view.GreetView;

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
     * Executes the {@link GreetCommand}, print a welcome message greeting the user.
     * <p>
     * This method display the exit message by initialising the exit view.
     *
     * @param storage The storage object that manages the data persistence. It is not used in {@link GreetCommand}
     */
    @Override
    public String execute(Storage storage) {
        return greetView.display();
    }
}
