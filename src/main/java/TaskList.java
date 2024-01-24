import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        return String.join("\n", IntStream.range(0, this.size())
                .boxed()
                .map(i -> {
                    Task task = this.get(i);
                    int index = i + 1;
                    return String.format("%d. %s", index, task.toString());
                })
                .toArray(String[]::new)
        );
    }
}
