import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        String message =
                "You and I are a team.\n" +
                "Here is the task list:\n";
        message += String.join("\n", IntStream.range(0, this.size())
                .boxed()
                .map(i -> {
                    Task task = this.get(i);
                    int index = i + 1;
                    return String.format("%d.%s", index, task.toString());
                })
                .toArray(String[]::new)
        );
        return message;
    }
}
