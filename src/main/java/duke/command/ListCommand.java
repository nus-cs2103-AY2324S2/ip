package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import java.util.List;

public class ListCommand extends Command<List<Task>> {
    public ListCommand() {
        super("list", List.of());
    }

    @Override
    public List<Task> execute(List<Task> tasks) {
        System.out.printf("\nHere's your list! ~~(^-^)\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
        return tasks;
    }
}
