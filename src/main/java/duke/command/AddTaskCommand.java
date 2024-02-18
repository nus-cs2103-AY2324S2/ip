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
        String printStatement;

        if (type.equals("todo")) {
            curr = generateTodo();
        } else if (type.equals("deadline")) {
            curr = generateDeadline();
        } else {
            curr = generateEvent();
        }
        t.addTask(curr);
        s.save(t.getList());
        return generatePrintStatement(t, curr);
    }


    public Task generateTodo() throws BelleException {
        try {
            int todoLength = 5;
            Task curr = new TodoTask(this.msg.substring(todoLength), false);
            return curr;
        } catch (StringIndexOutOfBoundsException e) {
            throw new BelleException("You did not specify a title for this todo task");
        }
    }

    public Task generateDeadline() throws BelleException {
        try {
            int deadlineLength = 9;
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
    public Task generateEvent() throws BelleException {
        assert (type.equals("event")) : "task is of an invalid type";
        try {
            int eventLength = 6;
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

    public String generatePrintStatement(TaskList t, Task curr) {
        return "--------------------------" + "\n"
                + "Got it. I've added this task:" + "\n" + curr.toString()
                + "\n" + "Now you have " + t.getSize() + " tasks in the list."
                + "\n" + "--------------------------";
    }
}
