package reacher.command;

import java.time.LocalDate;

import reacher.*;
import reacher.task.*;
import reacher.ui.MainWindow;

/**
 * Command that when executed, adds a task according to user to Task list and file storage.
 */
public class AddCommand extends Command {
    /**
     * Executes command by asking user for name and type of task and adding to tasks and update storage.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Local file storage.
     * @return
     * @throws ReacherException if user input is invalid type of task.
     */
    public String execute(String input, TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        String name = Parser.getInfo(input, 1);
        String type = Parser.getInfo(input, 2);

        Task t;
        switch (type) {
        case ("todo"):
            t = new Todos(name);
            tasks.addTask(t);
            storage.storeList(tasks.getTasks());
            return ("I've added " + t.toString());
        case ("deadline"):
            String deadlineS = Parser.getInfo(input, 3);
            LocalDate  deadlineD = LocalDate.parse(deadlineS);
            t = new Deadline(name, deadlineD);
            tasks.addTask(t);
            storage.storeList(tasks.getTasks());
            return ("I've added " + t.toString());
        case ("event"):
            LocalDate start = LocalDate.parse(Parser.getInfo(input, 3));
            LocalDate end = LocalDate.parse(Parser.getInfo(input, 4));
            if (start.isAfter(end)) {
                throw new ReacherException("End cannot be before start.");
            }
            t = new Events(name, start, end);
            tasks.addTask(t);
            storage.storeList(tasks.getTasks());
            return("I've added " + t.toString());
        default:
            throw new ReacherException("That is not a type of task.");
        }
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
