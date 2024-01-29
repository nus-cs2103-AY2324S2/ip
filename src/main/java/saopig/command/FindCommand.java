package saopig.command;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{

    private String command;
    private int typeIndex;

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }
    /**
     * Constructs a find command.
     *
     * @param command   The command.
     * @param typeIndex The type index.
     */
    public FindCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private void findTask(String input, TaskList tasks, Ui ui) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            String processedInput = input.substring(5);
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks.getTasks()) {
                if (task.getDescription().toLowerCase().contains(processedInput.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                ui.printMessage("No matching tasks found.");
                return;
            }

            ui.printMessage("Here are the matching tasks in your list:");

            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.printMessage((i + 1) + ". " + matchingTasks.get(i).toString());
            }

        } catch (SaopigInvaildSizeException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        findTask(command, tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
