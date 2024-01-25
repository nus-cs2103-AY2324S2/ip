import java.util.List;

public class UnknownCommand extends Command<Void> {
    public UnknownCommand() {
        super("", List.of());
    }

    Void execute(List<Task> tasks) {
        System.out.printf("sry idk what u meant\n");
        return null;
    }
}
