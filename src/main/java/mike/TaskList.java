package mike;

import java.util.ArrayList;

import mike.task.Task;

/**
 * TaskList is a wrapper class for an ArrayList of {@link Task} objects.
 * @author ningc
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Filters the view of the task list to tasks permitted by ListView object.
     * @param listView
     * @return string representation of filtered task list.
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
