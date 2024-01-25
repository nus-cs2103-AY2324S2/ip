import java.util.List;

class ListCommand extends Command<Void> {
    ListCommand() {
        super("list", List.of());
    }

    @Override
    Void execute(List<Task> tasks) {
        System.out.printf("\n(^-^)~~   Here's your list!\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        return null;
    }
}
