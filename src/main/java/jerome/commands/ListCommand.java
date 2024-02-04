package jerome.commands;

import jerome.tasklist.Task;

/**
 * Represents the command to display list of all entries in database.
 */
public class ListCommand extends Command {
    /**
     * Represents the command word for List Event command.
     */
    public static final String COMMAND_WORD = "list";

    /**
     * Represents how each individual event should be formatted.
     */
    private static final String MESSAGE_INDIVIDUAL_LISTING_FORMAT = "\t %d. %s";

    /**
     * Executes the command to display a list of all entries in the database.
     *
     * @return a CommandResult containing the formatted list of entries.
     */
    @Override
    public CommandResult execute() {

        StringBuilder listViewBuilder = new StringBuilder();

        for (int i = 0; i < dataStorage.getTaskCount(); i++) {
            Task currentTask = dataStorage.getTask(i);
            listViewBuilder.append(String.format(MESSAGE_INDIVIDUAL_LISTING_FORMAT, i + 1,
                    currentTask.toString()));

            // Append blank line if it is not last line.
            if (i < dataStorage.getTaskCount() - 1) {
                listViewBuilder.append(System.lineSeparator());
            }
        }

        return new CommandResult(listViewBuilder.toString());
    }

}
