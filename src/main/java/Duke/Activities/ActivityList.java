package Duke.Activities;

import Duke.Exception.CommandException;
import Duke.LocalStorage.LocalList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The {@code ActivityList} class manages a list of {@link Activity} objects. It supports adding, printing,
 * marking, and deleting activities. This class interacts with a local file system through {@link LocalList}
 * to persist activities across sessions.
 */
public class ActivityList {
    private final LocalList localList;
    private final ArrayList<Activity> activities;

    public ActivityList(String filePath) {
        this.localList = new LocalList(filePath);
        activities = new ArrayList<>();
        localList.read(this);
    }

    public Activity add(String name) {
        Activity activity = Activity.of(name);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    public Activity add(String name, LocalDate date) {
        Activity activity = Activity.of(name, date);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    public Activity add(String name, LocalDate start, LocalDate end) {
        Activity activity = Activity.of(name, start, end);
        activities.add(activity);
        localList.write(toStorageList());
        return activity;
    }

    public void mark(int index) throws CommandException {
        boolean isMark = activities.get(index).mark();
        if (isMark) {
            localList.write(toStorageList());
        } else {
            throw new CommandException("activity already marked");
        }
    }

    public void unmark(int index) throws CommandException {
        boolean isMark = activities.get(index).unmark();
        localList.write(toStorageList());
        if (isMark) {
            localList.write(toStorageList());
        } else {
            throw new CommandException("activity already marked");
        }
    }

    public void delete(int index) throws CommandException {
        try {
            activities.remove(index - 1);
            localList.write(toStorageList());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("activity not found at index: " + index);
        }
    }

    public ArrayList<Activity> find(String name) {
        ArrayList<Activity> relevantAct = new ArrayList<>();
        for (Activity activity : activities) {
            if (activity.checkName(name)) {
                relevantAct.add(activity);
            }
        }
        return relevantAct;
    }

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

    public String printActivities() {
        StringBuilder result = new StringBuilder();
        for(Activity act : activities) {
            result.append(act).append("\n");
        }
        return result.toString();
    }

    public String[] toStorageList() {
        String[] result = new String[activities.size()];
        for (int i = 0; i < activities.size(); i++) {
            result[i] = activities.get(i).toStorage();
        }
        return result;
    }
}