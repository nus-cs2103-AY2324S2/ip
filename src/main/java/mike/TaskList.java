package mike;

import java.util.ArrayList;

import mike.task.Task;

/**
 * TaskList is a wrapper class for an ArrayList of {@link Task} objects.
 * @author ningc
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Returns a filtered view of the task list to tasks permitted by the ListView object.
     * @param listView
     * @return Filtered task list represented as a String.
     */
    public String view(ListView listView) {
        return this
                .stream()
                .filter(task -> task.inListView(listView))
                .map(task -> {
                    int index = this.indexOf(task) + 1;
                    return index + "." + task;
                })
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

}
