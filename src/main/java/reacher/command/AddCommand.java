package reacher.command;

import java.time.LocalDate;

import reacher.*;
import reacher.task.*;
import reacher.ui.MainWindow;
import static reacher.Parser.Variable.*;

/**
 * Command that when executed, adds a task according to user to Task list and file storage.
 */
public class AddCommand extends Command {
    /**
     * Executes command by asking user for name and type of task and adding to tasks and update storage
     * and returns String for output.
     *
     * @param tasks   List of tasks.
     * @param storage Local file storage.
     * @throws ReacherException if user input is invalid type of task.
     */
    public String execute(String input, TaskList tasks, Storage storage) throws ReacherException {
        String name = Parser.getInfo(input, NAME);
        String type = Parser.getInfo(input, TYPE);

        Task t = null;
        switch (type) {
        case ("todo"):
            t = new Todos(name);
        case ("deadline"):
            String deadlineString = Parser.getInfo(input, DEADLINE);
            LocalDate  deadlineDate = LocalDate.parse(deadlineString);

            t = new Deadline(name, deadlineDate);
        case ("event"):
            LocalDate start = LocalDate.parse(Parser.getInfo(input, START));
            LocalDate end = LocalDate.parse(Parser.getInfo(input, END));

            if (start.isAfter(end)) {
                throw new ReacherException("End cannot be before start.");
            }

            t = new Events(name, start, end);
        }
        if (t == null) {
            throw new ReacherException("Type of tasks are: todo, deadline and event.");
        }
        tasks.addTask(t);
        storage.storeList(tasks.getTasks());
        return ("I've added " + t.toString());
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
