package fishstock;

import java.util.ArrayList;

import fishstock.FishStock.Command;

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

    /**
     * Get list.
     * @return The list.
     */
    protected ArrayList<Task> getList() {
        return list;
    }

    /**
     * Marks whether Task is done.
     * @param command The command.
     * @param input The input from user.
     * @return The marked/unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    protected Task changeMark(Command command, String input) throws FishStockException {
        Integer idx = Parser.getTaskFromIndex(input);
        try {
            Task task = list.get(idx);
            if (command == Command.MARK) {
                task.markAsDone();
            } else if (command == Command.UNMARK) {
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
        Integer idx = Parser.getTaskFromIndex(input);
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
     * @param command The command.
     * @param input The input from user.
     * @return The added Task.
     * @throws FishStockException The exceptions while adding the Task.
     */
    protected Task addTask(Command command, String input) throws FishStockException {
        Task task = null;
        switch (command) {
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

    protected ArrayList<Task> findTask(String input) throws FishStockException {
        if (input.length() < 6) {
            throw new FishStockException("OH NOSE! The match word is empty..");
        }

        String match = input.substring(5);

        ArrayList<Task> result = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(match)) {
                result.add(task);
            }
        }
        return result;
    }
}
