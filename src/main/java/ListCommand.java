import java.util.List;

class ListCommand extends Command<List<Task>> {
    ListCommand() {
        super("list", List.of());
    }

    @Override
    List<Task> execute(List<Task> tasks) {
        System.out.printf("\nHere's your list! ~~(^-^)\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        return tasks;
    }
}
