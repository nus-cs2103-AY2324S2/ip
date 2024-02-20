import lai.task.Deadline;
import lai.task.Event;
import lai.task.Task;
import lai.task.ToDo;
import lai.util.*;

import java.util.List;

public class Lai {

    private Storage storage = new Storage();
    private TaskList tasks;


    /**
     * Constructs a Lai object, .
     */
    public Lai() {
        tasks = new TaskList(storage.readTasksFile());
    }

    public String getResponse (String input) {
        String[] parsedInput = Parser.parse(input);
        String command = parsedInput[0];
        String desc = parsedInput[1];

        try {
            if (command.equals("mark")) {
                Parser.checkDescription(desc);

                int index = Integer.valueOf(desc);
                Task t = tasks.get(index - 1);
                t.setDone();
                storage.updateTasksFile(tasks);

                return Ui.printTaskMarked(t);
            } else if (command.equals("unmark")) {
                Parser.checkDescription(desc);

                int index = Integer.valueOf(desc);
                Task t = tasks.get(index - 1);
                t.setNotDone();
                storage.updateTasksFile(tasks);

                return Ui.printTaskUnmarked(t);
            } else if (command.equals("delete")) {
                int index = Integer.valueOf(desc);
                Task t = tasks.get(index - 1);
                tasks.remove(t);
                storage.updateTasksFile(tasks);

                return Ui.printTaskDeleted(t);
            } else if (command.equals("deadline")) {
                Parser.checkDescription(desc);

                // Separating the deadline from description
                String[] descBy = desc.split(" /by ", 2);
                desc = descBy[0];
                String by = "";
                if (descBy.length > 1) {
                    by = descBy[1];
                }

                Deadline newTask = new Deadline(desc, by);

                return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
            } else if (command.equals("todo")) {
                Parser.checkDescription(desc);

                ToDo newTask = new ToDo(desc);

                return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
            } else if (command.equals("event")) {
                Parser.checkDescription(desc);

                // Separating the start from description
                String[] descFrom = desc.split(" /from ", 2);
                desc = descFrom[0];
                String from = "";
                if (descFrom.length > 1) {
                    from = descFrom[1];
                }
                // Separating the end out
                String[] fromTo = from.split(" /to ", 2);
                from = fromTo[0];
                String to = "";
                if (fromTo.length > 1) {
                    to = fromTo[1];
                }

                Event newTask = new Event(desc, from, to);

                return Ui.printTaskAdded(newTask, tasks.add(newTask, storage));
            } else if (command.equals("list")) {
                return Ui.listTasks(tasks);
            } else if (command.equals("find")) {
                List<Task> result = tasks.find(desc);
                return Ui.listTasks(new TaskList(result));
            } else {
                throw new LaiException("I don't recognise this command at all. You likely made an extremely " +
                        "disappointing mistake, or I did. I can't tell for sure.");
            }
        } catch (LaiException e) {
            return Ui.printLaiException(e);
        } catch (NumberFormatException e) {
            return Ui.printNumberFormatException(e);
        }
    }
}
