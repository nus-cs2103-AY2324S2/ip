package fishstock;

import java.util.ArrayList;

import fishstock.Command.Keyword;

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
    protected Task markTask(String input) throws FishStockException {
        return changeMark(Keyword.MARK, input);
    }

    /**
     * Unmarks the Task.
     * @param input The input from user.
     * @return The unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    protected Task unmarkTask(String input) throws FishStockException {
        return changeMark(Keyword.UNMARK, input);
    }

    /**
     * Marks whether Task is done.
     * @param keyword The keyword command.
     * @param input The input from user.
     * @return The marked/unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    private Task changeMark(Keyword keyword, String input) throws FishStockException {
        Integer idx = Parser.getIndexFromInput(input);
        try {
            Task task = list.get(idx);
            if (keyword == Keyword.MARK) {
                task.markAsDone();
            } else if (keyword == Keyword.UNMARK) {
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
    protected Task deleteTask(String input) throws FishStockException {
        Integer idx = Parser.getIndexFromInput(input);
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
     * @param keyword The command.
     * @param input The input from user.
     * @return The added Task.
     * @throws FishStockException The exceptions while adding the Task.
     */
    protected Task addTask(Keyword keyword, String input) throws FishStockException {
        Task task = null;
        switch (keyword) {
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
    protected String findTasks(String input) throws FishStockException {
        if (input.length() < 6) {
            throw new FishStockException("OH NOSE! The match word is empty..");
        }

        String match = input.substring(5);

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
