package reacher.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import reacher.*;
import reacher.task.*;

/**
 * Command that when executed, adds a task according to user to Task list and file storage.
 */
public class AddCommand extends Command {
    /**
     * Executes command by asking user for name and type of task and adding to tasks and update storage.
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Local file storage.
     * @throws ReacherException if user input is invalid type of task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        ui.print("What is the name of the task?");
        String name = ui.readString();

        ui.print("What type of task is this?(Deadline, Event, Todo)");
        String type = ui.readString().toLowerCase();

        Task t;
        switch (type) {
        case ("todo"):
            t = new Todos(name);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
            break;
        case ("deadline"):
            ui.print("When is the deadline?");
            LocalDate deadline = null;
            deadline = LocalDate.parse(ui.readString());
            t = new Deadline(name, deadline);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
            break;
        case ("event"):
            ui.print("When is the start?");
            LocalDate start = LocalDate.parse(ui.readString());
            ui.print("When is the end?");
            LocalDate end = LocalDate.parse(ui.readString());
            if (start.isAfter(end)) {
                throw new ReacherException("End cannot be before start.");
            }
            t = new Events(name, start, end);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
            break;
        default:
            throw new ReacherException("That is not a type of task.");
        }
        storage.storeList(tasks.getTasks());
    }

    /**
     * Checks if object is an instance of AddCommand class.
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        return object instanceof AddCommand;
    }
}
