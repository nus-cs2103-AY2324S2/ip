package belle.command;

import belle.others.BelleException;
import belle.run.Storage;
import belle.run.TaskList;
import belle.run.Ui;
import belle.tasks.DeadlineTask;
import belle.tasks.EventTask;
import belle.tasks.Task;
import belle.tasks.TodoTask;

/**
 * Adds item to tasklist.
 */
public class AddTaskCommand extends Command {
    private String type;
    private String msg;

    private enum Type {
        T,
        D,
        E
    }

    /**
     * Constructs AddTaskCommand.
     *
     * @param type Type of command.
     * @param msg The remaining part of input string,
     *            excluding the type.
     */
    public AddTaskCommand(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    /**
     * Runs the command to add task to tasklist.
     *
     * @param storage Storage containing data of
     *                previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for add command.
     * @throws BelleException If task being added is an
     *         invalid type.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException {
        Task currTask;
        if (type.equals(Type.T.name())) {
            currTask = generateTodo();
        } else if (type.equals(Type.D.name())) {
            currTask = generateDeadline();
        } else if (type.equals(Type.E.name())) {
            currTask = generateEvent();
        } else {
            throw new BelleException("You are trying to add an invalid type to the list.");
        }

        assert (currTask != null) : "current task to be added is null";

        taskList.addTask(currTask);
        storage.save(taskList.getList());
        return generateAddStatement(taskList, currTask);
    }

    /**
     * Generates TodoTask based on message.
     *
     * @return TodoTask based on message.
     * @throws BelleException If did not specify a
     *         title in the user input.
     */
    public Task generateTodo() throws BelleException {
        try {
            int todoLength = 5; // as todo + 1 space is 5 characters.
            Task curr = new TodoTask(this.msg.substring(todoLength), false);
            return curr;
        } catch (StringIndexOutOfBoundsException e) {
            throw new BelleException("You did not specify a"
                    + " title for this todo task");
        }
    }

    /**
     * Generates DeadlineTask based on message.
     *
     * @return DeadlineTask based on message.
     * @throws BelleException If did not specify all
     *         required information in the user input.
     */
    public Task generateDeadline() throws BelleException {
        try {
            assert (msg.length() >= 9) : "invalid input to generate deadline task";

            int deadlineLength = 9; // as deadline + 1 space is 9 characters.
            String[] deadlineList = msg.substring(deadlineLength).split(" /by ");
            Task curr = new DeadlineTask(deadlineList[0], false, deadlineList[1]);
            return curr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BelleException("You did not specify "
                    + "all the required information for deadline task.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new BelleException("You did not specify "
                    + "all the required information for deadline task.");
        }
    }

    /**
     * Generates EventTask based on message.
     *
     * @return EventTask based on message.
     * @throws BelleException If did not specify all
     *         required information in the user input.
     */
    public Task generateEvent() throws BelleException {
        try {
            assert (msg.length() >= 6) : "invalid input to generate event task";

            int eventLength = 6; // as event + 1 space is 6 characters.
            String[] eventList = msg.substring(eventLength).split(" /from ");
            String[] startEndList = eventList[1].split(" /to ");
            Task curr = new EventTask(eventList[0], false, startEndList[0], startEndList[1]);
            return curr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new BelleException("You did not "
                    + "specify all the required information for deadline task.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new BelleException("You did not "
                    + "specify all the required information for deadline task.");
        }
    }

    /**
     * Generates print statement when adding
     * a particular task to the list.
     *
     * @param t Tasklist.
     * @param curr Current task to add to list.
     * @return Print statement for adding task
     *         based on message.
     */
    public String generateAddStatement(TaskList t, Task curr) {
        return "--------------------------" + "\n"
                + "Got it. I've added this task:" + "\n" + curr.toString()
                + "\n" + "Now you have " + t.getSize() + " tasks in the list."
                + "\n" + "--------------------------";
    }
}
