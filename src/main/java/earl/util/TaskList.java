package earl.util;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import earl.tasks.Task;

/**
 * Class encapsulating the list of tasks.
 * <p>
 * Functionally a wrapper of the {@code List} class. Passes information
 * as a {@code Stream}.
 */
public class TaskList {

    private final List<Task> tasks;

    /** Class constructor. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Class constructor specifying existing list. */
    public TaskList(Stream<Task> tasks) {
        this.tasks = tasks.collect(toCollection(ArrayList::new));
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int idx) {
        return tasks.remove(idx);
    }

    public Stream<String> getAsIndexedStream() {
        return IntStream.range(0, tasks.size())
                .mapToObj((idx) -> idx + 1 + "." + tasks.get(idx));
    }

    public Stream<String> getAsStorageStringStream() {
        return tasks.stream().map(Task::toStorageString);
    }
}
