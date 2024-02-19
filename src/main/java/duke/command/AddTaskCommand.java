package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * Adds item to tasklist.
 */
public class AddTaskCommand extends Command {
    private String type;
    private String msg;

    enum Type {
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
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     */
    @Override
    public String execute(Storage s, TaskList t, Ui u) throws BelleException {
        Task curr;
        if (type.equals(Type.T.name())) {
            curr = generateTodo();
        } else if (type.equals(Type.D.name())) {
            curr = generateDeadline();
        } else if (type.equals(Type.E.name())) {
            curr = generateEvent();
        } else {
            throw new BelleException("You are trying to add an invalid type to the list.");
        }

        assert (curr != null) : "current task to be added is null";

        t.addTask(curr);
        s.save(t.getList());
        return generateAddStatement(t, curr);
    }


    /**
     * Generates TodoTask based on message.
     *
     * @return TodoTask based on message.
     * @throws BelleException If did not specify a title.
     */
    public Task generateTodo() throws BelleException {
        try {
            assert (msg.length() >= 5) : "invalid input to generate todo task";
            int todoLength = 5; // as todo + 1 space is 5 characters.
            Task curr = new TodoTask(this.msg.substring(todoLength), false);
            return curr;
        } catch (StringIndexOutOfBoundsException e) {
            throw new BelleException("You did not specify a title for this todo task");
        }
    }

    /**
     * Generates DeadlineTask based on message.
     *
     * @return DeadlineTask based on message.
     * @throws BelleException If did not specify all
     *     required information.
     */
    public Task generateDeadline() throws BelleException {
        try {
            assert (msg.length() >= 9) : "invalid input to generate deadline task";
            int deadlineLength = 9; // as deadline + 1 space is 9 characters.
            String[] deadlinelist = msg.substring(deadlineLength).split(" /by ");
            Task curr = new DeadlineTask(deadlinelist[0], false, deadlinelist[1]);
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
     *     required information.
     */
    public Task generateEvent() throws BelleException {
        assert (msg.length() >= 6) : "invalid input to generate event task";
        try {
            int eventLength = 6; // as event + 1 space is 6 characters.
            String[] eventlist = msg.substring(eventLength).split(" /from ");
            String[] startend = eventlist[1].split(" /to ");
            Task curr = new EventTask(eventlist[0], false, startend[0], startend[1]);
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
     */
    public String generateAddStatement(TaskList t, Task curr) {
        return "--------------------------" + "\n"
                + "Got it. I've added this task:" + "\n" + curr.toString()
                + "\n" + "Now you have " + t.getSize() + " tasks in the list."
                + "\n" + "--------------------------";
    }
}
