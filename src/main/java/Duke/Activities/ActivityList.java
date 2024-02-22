package Duke.Activities;

import Duke.Exception.CommandException;
import Duke.LocalStorage.LocalList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a list of activities managed by the application.
 */
public class ActivityList {

    /**
     * The local list handler for reading and writing activities to a file.
     */
    private final LocalList localList;

    /**
     * The list of activities stored in memory.
     */
    private final ArrayList<Activity> activities;

    /**
     * Constructor to initialize the activity list with a specified file path.
     *
     * @param filePath The file path for reading and writing activities.
     */
    public ActivityList(String filePath) {
        this.localList = new LocalList(filePath);
        activities = new ArrayList<>();
        localList.read(this);
    }

    /**
     * Adds a Todo activity with the specified name to the list and updates the storage.
     *
     * @param name The name of the Todo activity.
     * @return The added Todo activity.
     */
    public Activity add(String name) {
        Activity activity = Activity.of(name);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    /**
     * Adds a Deadline activity with the specified name and date to the list and updates the storage.
     *
     * @param name The name of the Deadline activity.
     * @param date The deadline date.
     * @return The added Deadline activity.
     */
    public Activity add(String name, LocalDate date) {
        Activity activity = Activity.of(name, date);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    /**
     * Adds an Event activity with the specified name, start, and end dates to the list and updates the storage.
     *
     * @param name  The name of the Event activity.
     * @param start The start date of the event.
     * @param end   The end date of the event.
     * @return The added Event activity.
     */
    public Activity add(String name, LocalDate start, LocalDate end) {
        Activity activity = Activity.of(name, start, end);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    /**
     * Marks the activity at the specified index as completed and updates the storage.
     *
     * @param index The index of the activity to mark.
     * @throws CommandException If the activity is already marked or the index is out of bounds.
     */
    public void mark(int index) throws CommandException {
        boolean isMark = activities.get(index).mark();
        if (isMark) {
            localList.write(toStorageList());
        } else {
            throw new CommandException("activity already marked");
        }
    }

    /**
     * Unmarks the activity at the specified index and updates the storage.
     *
     * @param index The index of the activity to unmark.
     * @throws CommandException If the activity is already marked or the index is out of bounds.
     */
    public void unmark(int index) throws CommandException {
        boolean isMark = activities.get(index).unmark();
        localList.write(toStorageList());
        if (isMark) {
            localList.write(toStorageList());
        } else {
            throw new CommandException("activity already marked");
        }
    }

    /**
     * Deletes the activity at the specified index and updates the storage.
     *
     * @param index The index of the activity to delete.
     * @throws CommandException If the index is out of bounds.
     */
    public void delete(int index) throws CommandException {
        try {
            activities.remove(index - 1);
            localList.write(toStorageList());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("activity not found at index: " + index);
        }
    }

    /**
     * Finds activities with names containing the specified string.
     *
     * @param name The string to match against activity names.
     * @return A list of activities with names containing the specified string.
     */
    public ArrayList<Activity> find(String name) {
        ArrayList<Activity> relevantAct = new ArrayList<>();
        for (Activity activity : activities) {
            if (activity.checkName(name)) {
                relevantAct.add(activity);
            }
        }
        return relevantAct;
    }

    /**
     * Finds the index of an activity with an exact name match.
     *
     * @param name The exact name to match against activity names.
     * @return The index of the activity with an exact name match, or -1 if not found.
     */
    public int findExact(String name) {
        int index = -1;
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i).checkExactName(name)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Returns a formatted string representation of all activities in the list.
     *
     * @return A formatted string containing details of all activities in the list.
     */
    public String printActivities() {
        StringBuilder result = new StringBuilder();
        for (Activity act : activities) {
            result.append(act).append("\n");
        }
        return result.toString();
    }

    /**
     * Converts the list of activities to an array of storage-friendly strings.
     *
     * @return An array of strings representing the activities in a storage-friendly format.
     */
    public String[] toStorageList() {
        String[] result = new String[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            result[i] = activities.get(i).toStorage();
        }
        return result;
    }
}
