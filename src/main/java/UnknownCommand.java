import java.util.List;

public class UnknownCommand extends Command<List<Task>> {
    public UnknownCommand() {
        super("", List.of());
    }

    List<Task> execute(List<Task> tasks) {
        return null;
    }
}
