import java.util.List;

public class UnknownCommand extends Command<List<Task>> {
    public UnknownCommand() {
        super("", List.of());
    }

    List<Task> execute(List<Task> tasks) {
        // To-do: UI
        System.out.printf("sry idk what u meant\n");
        return null;
    }
}
