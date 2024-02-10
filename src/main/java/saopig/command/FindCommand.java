package saopig.command;

import java.util.ArrayList;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

/**
 * Represents a command to find tasks
 */
public class FindCommand extends Command {
    private String command;
    private int typeIndex;

    /**
     * Constructs a find command.
     *
     * @param command   The command.
     * @param typeIndex The type index.
     */
    public FindCommand(String command, int typeIndex) {
        assert typeIndex == 0 : "typeIndex should be 0";
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    private String findTask(String input, TaskList tasks, Ui ui) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            assert input.length() >= 6 : "Input length should be at least 6";
            String processedInput = input.substring(5);
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks.getTasks()) {
                if (task.getDescription().toLowerCase().contains(processedInput.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                return ("No matching tasks found.");
            }
            StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");

            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append((i + 1)).append(". ").append(matchingTasks.get(i).toString());
            }
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return ("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return findTask(command, tasks, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
