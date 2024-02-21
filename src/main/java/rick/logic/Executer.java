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
                return "Alright, Morty, I've marked that task as done for you:\n" + markTask
                        + "\nYou're making progress, Morty!";
            case ("U"):
                int uIndex = Integer.parseInt(command.respond()[1]);
                Task unmarkTask = this.taskList.unmark(uIndex);
                this.storage.update();
                return "Fine, Morty, I've marked that task as not done yet:\n" + unmarkTask
                        + "\nMorty, I can't believe you didn't complete the task! "
                        + "What were you doing, Morty? You need to get your act together "
                        + "and finish what you started. Don't let me down, Morty!";
            case ("L"):
                return this.taskList.list();
            case ("D"):
                int dIndex = Integer.parseInt(command.respond()[1]);
                Task task = this.taskList.delete(dIndex);
                this.storage.update();
                return "Fine, Morty, I've removed that pointless task for you. Here's your list now:\n"
                        + task
                        + "\nNow you have " + this.taskList.getSize() + " tasks in the list. "
                        + "Try not to waste my time with this stuff again, Morty.";
            case ("F"):
                String substring = command.respond()[1];
                return this.taskList.find(substring);
            case ("T"):
                Task tTask = this.taskList.addToList(command.respond()[1]);
                this.storage.update();
                return "Fine, Morty, I'll add your stupid task to the list:\n"
                    + tTask
                    + "\nThere, you happy now? You've got " + this.taskList.getSize() + " other pointless tasks in your stupid list.";
            case ("DL"):
                Task dTask = this.taskList.addToList(command.respond()[1], command.respond()[2]);
                this.storage.update();
                return "Fine, Morty, I've added that deadline for you:\n"
                        + dTask
                        + "\nNow you've got " + this.taskList.getSize() + " things to stress about.";
            case ("E"):
                Task eTask = this.taskList.addToList(command.respond()[1], command.respond()[2],
                        command.respond()[3]);
                this.storage.update();
                return "Fine, Morty, I've added your stupid event to the list:\n"
                        + eTask
                        + "\nNow you have " + this.taskList.getSize() + " tasks in the list."
                        + "Don't expect me to remind you about it, Morty.\"";
            default:
                throw new RickException("Hey Morty, it's Rick. I can't believe this, "
                        + "but I'm stuck in some kind of interdimensional time loop. "
                        + "Every time I try to fix the parser, I end up back at the beginning. "
                        + "It's like Groundhog Day, but with more aliens. "
                        + "Anyway, drop me a report at https://forms.gle/hnnDTA7qYMnhJvQ46, and hopefully, "
                        + "I can figure this out from there. Thanks, Morty. You're a real pal.");
            }
        } catch (RickException e) {
            throw e;
        }
    }
}
