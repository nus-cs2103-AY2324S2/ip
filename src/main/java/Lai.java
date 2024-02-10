import lai.task.Deadline;
import lai.task.Event;
import lai.task.Task;
import lai.task.ToDo;
import lai.util.*;

import java.util.List;
import java.util.Scanner;

public class Lai {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.readTasksFile());
        Ui ui = new Ui(scanner);

        ui.printStart();
        String[] input = ui.getInput();
        String command = input[0];
        String desc = input[1];

        while (!command.equals("bye")) {
            try {
                if (command.equals("mark")) {
                    Parser.checkDescription(desc);

                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    t.setDone();
                    storage.updateTasksFile(tasks);

                    ui.printTaskMarked(t);
                } else if (command.equals("unmark")) {
                    Parser.checkDescription(desc);

                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    t.setNotDone();
                    storage.updateTasksFile(tasks);

                    ui.printTaskUnmarked(t);
                } else if (command.equals("delete")) {
                    int index = Integer.valueOf(desc);
                    Task t = tasks.get(index - 1);
                    tasks.remove(t);
                    storage.updateTasksFile(tasks);

                    ui.printTaskDeleted(t);
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
                    tasks.add(newTask, storage);
                } else if (command.equals("todo")) {
                    Parser.checkDescription(desc);

                    ToDo newTask = new ToDo(desc);
                    tasks.add(newTask, storage);
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
                    tasks.add(newTask, storage);
                } else if (command.equals("list")) {
                    ui.listTasks(tasks);
                } else if (command.equals("find")) {
                    List<Task> result = tasks.find(desc);
                    ui.listTasks(new TaskList(result));
                } else {
                    throw new LaiException("I don't recognise this command at all. You likely made an extremely " +
                            "disappointing mistake, or I did. I can't tell for sure.");
                }

                input = ui.getInput();
                command = input[0];
                desc = input[1];
            } catch (LaiException e) {
                ui.printLaiException(e);

                input = ui.getInput();
                command = input[0];
                desc = input[1];
            } catch (NumberFormatException e) {
                ui.printNumberFormatException(e);

                input = ui.getInput();
                command = input[0];
                desc = input[1];
            }
        }

        ui.printBye();
    }
}
