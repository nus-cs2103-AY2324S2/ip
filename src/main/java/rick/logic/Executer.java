package rick.logic;

import rick.logic.command.Command;
import rick.tasks.Task;
import rick.util.Storage;
import rick.util.TaskList;

/**
 * An executer for parsed commands regarding a designated task list.
 */
public class Executer {
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor of executer. Initializes taskList and storage to the ones in the corresponding Rick instance.
     * @param taskList the task list to use.
     * @param storage the storage to use.
     */
    public Executer(TaskList taskList, Storage storage) {
        assert taskList != null && storage != null;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Executes commands based on the type of command.
     * @param command the parsed command.
     * @return a string which is the response.
     */
    public String execute(Command command) throws RickException {
        try {
            assert command != null && command.respond()[0] != null;
            switch (command.respond()[0]) {
            case ("B"):
                return command.respond()[1];
            case ("M"):
                int mIndex = Integer.parseInt(command.respond()[1]);
                Task markTask = this.taskList.mark(mIndex);
                this.storage.update();
                return "Nice! I've marked this task as done:\n" + markTask;
            case ("U"):
                int uIndex = Integer.parseInt(command.respond()[1]);
                Task unmarkTask = this.taskList.unmark(uIndex);
                this.storage.update();
                return "OK, I've marked this task as not done yet:\n" + unmarkTask;
            case ("L"):
                return this.taskList.list();
            case ("D"):
                int dIndex = Integer.parseInt(command.respond()[1]);
                Task task = this.taskList.delete(dIndex);
                this.storage.update();
                return "Noted. I've removed this task:\n"
                        + task
                        + "\nNow you have " + this.taskList.getSize() + " tasks in the list.";
            case ("F"):
                String substring = command.respond()[1];
                return this.taskList.find(substring);
            case ("T"):
                Task tTask = this.taskList.addToList(command.respond()[1]);
                this.storage.update();
                return "Got it. I've added this task:\n"
                    + tTask
                    + "\nNow you have " + this.taskList.getSize() + " tasks in the list.";
            case ("DL"):
                Task dTask = this.taskList.addToList(command.respond()[1], command.respond()[2]);
                this.storage.update();
                return "Got it. I've added this task:\n"
                        + dTask
                        + "\nNow you have " + this.taskList.getSize() + " tasks in the list.";
            case ("E"):
                Task eTask = this.taskList.addToList(command.respond()[1], command.respond()[2],
                        command.respond()[3]);
                this.storage.update();
                return "Got it. I've added this task:\n"
                        + eTask
                        + "\nNow you have " + this.taskList.getSize() + " tasks in the list.";
            default:
                throw new RickException("Execution error: Unexpected first argument of command.");
            }
        } catch (RickException e) {
            throw e;
        }
    }
}
