import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklists.TaskList;
import ui.Ui;

import java.util.List;

/**
 * The chat-bot To-Do program named Howie which keeps tracks of major major tasks such as
 * todo, event and deadlines.
 * @author Koo Zhuo Hui
 */
public class Howie {
    private static Ui ui;
    private static TaskList taskLs;
    private static Storage storage;


    /**
     * Deletes a task from Howie's list.
     * @param tasks The collection of tasks.
     * @param i 1-Based index of the task to be deleted.
     */
    public static void delete(List<Task> tasks, int i) throws DukeException {
        if (i > tasks.size() || i <= 0) {
            Ui.printVLine();
            throw new DukeException("Hmm...I can't delete something that isn't there :O");
        } else {
            Task t = tasks.remove(i-1);
            Ui.printVLine();
            System.out.println("Okay! The following task has been removed:\n" + t);
            ui.printAllTask(tasks);
        }
    }

    /**
     * Prints a message when a blank task has been entered.
     */
    public static void emptyTaskMessage() throws DukeException {
        Ui.printVLine();
        throw new DukeException("Hey! You've just entered an unnamed task... Try to give a description/name of your task :)");
    }

    /**
     * Prints a message when an invalid format has been entered.
     */
    public static void invalidFormat() throws DukeException {
        Ui.printVLine();
        throw new DukeException("I see you've entered an invalid format. Type 'help' if you're unsure :)");
    }


    /**
     * Initialises the program.
     * @param args Input arguments.
     * @throws Exception Throws DukeException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        ui = new Ui();
        storage = new Storage();
        taskLs = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();

            Command command = new Parser().parseCommand(input);
            try {
                command.setData(taskLs);
                command.execute();
            } catch (DukeException e) {
                System.out.println(e.toString());
                Ui.printVLine();
            }
        }
    }
}
