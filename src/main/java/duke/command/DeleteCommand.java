package duke.command;

import duke.exception.*;
import duke.task.*;
import duke.ui.*;
import java.util.List;

public class DeleteCommand extends Command<List<Task>> {
    public DeleteCommand(List<String> arguments) {
        super("delete", arguments);
    }

    @Override
    public List<Task> execute(List<Task> tasks) throws DukeException {
        try {
            if (arguments.isEmpty()) {
                throw new DukeException("Hey, you need to tell me which one to delete! Try 'delete <NUMBER>'. ~(>_<)\n");
            }

            int index = Integer.parseInt(arguments.get(0)) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.printf("\nDeleted task %d ~(^-^)\n ", index + 1);
            } else {
                throw new DukeException(String.format("I can't do that.. Task index %s is out of range! ~(T_T)\n", arguments.get(0)));
            }
        } catch (DukeException e) {
            System.out.printf("%s", e.getMessage());
            return tasks;
        } catch (NumberFormatException e) {
            System.out.printf("Sigh.. That's not a valid number! Try 'delete <NUMBER>'.\n");
            return tasks;
        }

        return tasks;
    }
}
