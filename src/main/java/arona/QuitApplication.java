package arona;

/**
 * Allow for user to quit application.
 */
public class QuitApplication extends Command {
    public QuitApplication(String fullCommand) {
        super(fullCommand);
        this.exit = true;
    }

    /**
     * Quit the application.
     *
     * @param taskList The class that contains the tasklist.
     * @param ui UI that handles all the console output.
     * @param storage Handle the loading and saving of data.
     * @throws FileException if file cannot be found.
     * @throws TaskException if command input does not match the requirements.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.quitApplication();
    }
}