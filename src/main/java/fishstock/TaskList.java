package fishstock;

import java.util.ArrayList;

import fishstock.Command.CommandType;

/**
 * Encapsulates a TaskList object.
 * Handles all functions related to the array storing Tasks.
 */
class TaskList {
    protected final ArrayList<Task> list;

    protected TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    protected TaskList() {
        this.list = new ArrayList<>();
    }

    protected int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += (i + 1) + "." + list.get(i) + "\n";
        }
        return result;
    }

    /**
     * Marks the Task.
     * @param input The input from user.
     * @return The marked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    protected Task markTask(UserInput input) throws FishStockException {
        return changeMark(CommandType.MARK, input);
    }

    /**
     * Unmarks the Task.
     * @param input The input from user.
     * @return The unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    protected Task unmarkTask(UserInput input) throws FishStockException {
        return changeMark(CommandType.UNMARK, input);
    }

    /**
     * Marks whether Task is done.
     * @param commandType The type of command.
     * @param input The input from user.
     * @return The marked/unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    private Task changeMark(CommandType commandType, UserInput input) throws FishStockException {
        int idx = input.getIndex();
        try {
            Task task = list.get(idx);
            if (commandType == CommandType.MARK) {
                task.markAsDone();
            } else if (commandType == CommandType.UNMARK) {
                task.markAsUndone();
            }
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    /**
     * Removes Task from array.
     * @param input The input from user.
     * @return The removed Task.
     * @throws FishStockException The exceptions while removing the Task.
     */
    protected Task deleteTask(UserInput input) throws FishStockException {
        int idx = input.getIndex();
        try {
            Task task = list.get(idx);
            list.remove(task);
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    /**
     * Adds Task into array.
     * @param commandType The type of command.
     * @param input The input from user.
     * @return The added Task.
     * @throws FishStockException The exceptions while adding the Task.
     */
    protected Task addTask(CommandType commandType, UserInput input) throws FishStockException {
        Task task = null;
        switch (commandType) {
        case TODO:
            task = Todo.of(input);
            break;
        case DEADLINE:
            task = Deadline.of(input);
            break;
        case EVENT:
            task = Event.of(input);
            break;
        default:
            throw new FishStockException("Attempted to add an invalid Task..");
        }
        list.add(task);
        return task;
    }

    /**
     * Finds Tasks that contain input in description.
     * @param input The input to be matched with.
     * @return The Tasks that were found.
     * @throws FishStockException The exceptions while finding Tasks.
     */
    protected String findTasks(UserInput input) throws FishStockException {
        String[] splitInput = input.splitByKeywords();
        if (splitInput[0].isEmpty()) {
            throw new FishStockException("OH NOSE! The match word is empty..");
        }

        String match = splitInput[0];

        int count = 0;
        String result = "";
        for (Task task : list) {
            if (task.getDescription().contains(match)) {
                result += (++count) + "." + task + "\n";
            }
        }
        return result;
    }
}
