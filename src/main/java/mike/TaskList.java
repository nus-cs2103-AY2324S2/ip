package mike;

import mike.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * TaskList is a wrapper class for an ArrayList of {@link Task} objects.
 * @author ningc
 */
public class TaskList extends ArrayList<Task>  {
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
