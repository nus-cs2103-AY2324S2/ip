package harvard;

import java.time.LocalDate;
import harvard.tasks.Event;
import harvard.tasks.Deadline;
import harvard.tasks.Todo;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Parser(Storage s, TaskList tL, Ui ui) {
        this.storage = s;
        this.tasks = tL;
        this.ui = ui;
    }

    public void parse(String commandLine) {
        String command = commandLine.split(" ")[0];
        try {
            if (!command.equals("list") && !command.equals("todo") && !command.equals("deadline") &&
                    !command.equals("event") && !command.equals("mark") && !command.equals("unmark") &&
                    !command.equals("delete")) {
                throw new HarvardException("Bro... Idk what that is man.");
            }
        } catch (HarvardException e) {
            this.ui.printUnrecognisedCommand();
        }

        if (command.equals("list")) {
            this.ui.printTasks(tasks);
        }

        if (command.equals("delete")) {
            int index = Integer.parseInt(commandLine.split(" ")[1]);

            this.ui.printDeleteTask(tasks.getTask(index - 1), tasks.getSize() - 1);
            this.tasks.delete(index - 1);
        }

        if (command.equals("todo")) {
            try {
                if (commandLine.split(" ").length == 1) {
                    throw new HarvardException("Wow that's awkward... Please enter a description for todo!");
                }
                Todo todoTask = new Todo(commandLine.substring(commandLine.indexOf(' ') + 1));
                this.tasks.add(todoTask);
                this.ui.printAddTask(todoTask, tasks.getSize());
            } catch (HarvardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        if (command.equals("deadline")) {
            String[] commandItems = commandLine.split(" /by ");
            String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
            LocalDate by = LocalDate.parse(commandItems[1]);

            Deadline deadlineTask = new Deadline(desc, by);
            this.tasks.add(deadlineTask);
            this.ui.printAddTask(deadlineTask, tasks.getSize());
        }

        if (command.equals("event")) {
            String[] commandItems = commandLine.split(" /from ");
            String desc = commandItems[0].substring(commandItems[0].indexOf(' ')+1);
            String[] commandItems2 = commandItems[1].split(" /to ");
            LocalDate from = LocalDate.parse(commandItems2[0]);
            LocalDate to = LocalDate.parse(commandItems2[1]);

            Event eventTask = new Event(desc, from, to);
            this.tasks.add(eventTask);
            this.ui.printAddTask(eventTask, tasks.getSize());
        }

        if (command.equals("mark") || command.equals("unmark")) {
            int index = Integer.parseInt(commandLine.split(" ")[1]) - 1;

            if (command.equals("mark")) {
                this.tasks.mark(index);
                this.ui.printMark(this.tasks.getTask(index));
            } else {
                this.tasks.unmark(index);
                this.ui.printUnmark(this.tasks.getTask(index ));
            }

        }

        this.storage.store(this.tasks);
    }

    public static class HarvardException extends Throwable {
        public HarvardException(String errorMessage) {
            super(errorMessage);
        }
    }
}
