package commands;

import tasklist.Task;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private static final String MESSAGE_INDIVIDUAL_LISTING_FORMAT = "\t %d. %s";

    @Override
    public CommandResult execute() {

        StringBuilder listViewBuilder = new StringBuilder();

        for (int i = 0; i < dataStorage.getTaskCount(); i++) {
            Task currentTask = dataStorage.getTask(i);
            listViewBuilder.append(String.format(MESSAGE_INDIVIDUAL_LISTING_FORMAT, i + 1, currentTask.toString()));

            // Append blank line if it is not last line.
            if (i < dataStorage.getTaskCount() - 1) {
                listViewBuilder.append(System.lineSeparator());
            }
        }

        return new CommandResult(listViewBuilder.toString());
    }

}
