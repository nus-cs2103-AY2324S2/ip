package harvard;

import java.time.LocalDate;

import harvard.exceptions.HarvardException;
import harvard.tasks.Deadline;
import harvard.tasks.Event;
import harvard.tasks.Task;
import harvard.tasks.Todo;

/**
 * The Parser class is responsible for interpreting user commands in the Harvard application.
 * It processes user input and performs corresponding actions such as adding tasks, deleting tasks,
 * marking tasks as done, and displaying the task list.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Parser instance with references to Storage, TaskList, and Ui.
     *
     * @param s The Storage instance for storing tasks.
     * @param tL The TaskList instance for managing tasks.
     * @param ui The Ui instance for displaying messages.
     */
    public Parser(Storage s, TaskList tL, Ui ui) {
        this.storage = s;
        this.tasks = tL;
        this.ui = ui;
    }

    /**
     * Parses the user command and performs corresponding actions.
     *
     * @param commandLine The user command to be parsed.
     * @throws HarvardException If an error occurs during parsing or task manipulation.
     */
    public String parse(String commandLine) throws HarvardException {
        String command = commandLine.split(" ")[0];

        if (!command.equals("list") && !command.equals("todo")
                && !command.equals("deadline") && !command.equals("event")
                && !command.equals("mark") && !command.equals("unmark")
                && !command.equals("delete") && !command.equals("find")) {
            throw new HarvardException("Bro... Idk what that is man.");
        }

        if (command.equals("list")) {
            return this.ui.printTasks(tasks);
        }

        if (command.equals("find")) {
            String[] commandItems = commandLine.split(" ");
            TaskList filteredTasks = tasks.find(commandItems[1]);

            return this.ui.printFindTasks(filteredTasks);
        }

        if (command.equals("delete")) {
            int index = Integer.parseInt(commandLine.split(" ")[1]);
            int tasksLeft = tasks.getSize() - 1;
            Task taskLeft = tasks.getTask(index - 1);

            this.tasks.delete(index - 1);
            this.storage.store(this.tasks);
            return this.ui.printDeleteTask(taskLeft, tasksLeft);
        }

        if (command.equals("todo")) {
            try {
                if (commandLine.split(" ").length == 1) {
                    throw new HarvardException("Wow that's awkward... Please enter a description for todo!");
                }
                Todo todoTask = new Todo(commandLine.substring(commandLine.indexOf(' ') + 1));
                this.tasks.add(todoTask);
                this.storage.store(this.tasks);
                return this.ui.printAddTask(todoTask, tasks.getSize());
            } catch (HarvardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        if (command.equals("deadline")) {
            String[] commandItems = commandLine.split(" /by ");
            String desc = commandItems[0].substring(commandItems[0].indexOf(' ') + 1);
            LocalDate by = LocalDate.parse(commandItems[1]);

            Deadline deadlineTask = new Deadline(desc, by);
            this.tasks.add(deadlineTask);
            this.storage.store(this.tasks);
            return this.ui.printAddTask(deadlineTask, tasks.getSize());
        }

        if (command.equals("event")) {
            String[] commandItems = commandLine.split(" /from ");
            String desc = commandItems[0].substring(commandItems[0].indexOf(' ') + 1);
            String[] commandItems2 = commandItems[1].split(" /to ");
            LocalDate from = LocalDate.parse(commandItems2[0]);
            LocalDate to = LocalDate.parse(commandItems2[1]);

            Event eventTask = new Event(desc, from, to);
            this.tasks.add(eventTask);
            this.storage.store(this.tasks);
            return this.ui.printAddTask(eventTask, tasks.getSize());
        }

        if (command.equals("mark") || command.equals("unmark")) {
            int index = Integer.parseInt(commandLine.split(" ")[1]) - 1;

            if (command.equals("mark")) {
                this.tasks.mark(index);
                this.storage.store(this.tasks);
                return this.ui.printMark(this.tasks.getTask(index));
            } else {
                this.tasks.unmark(index);
                this.storage.store(this.tasks);
                return this.ui.printUnmark(this.tasks.getTask(index));
            }

        }
        return "";
    }
}
